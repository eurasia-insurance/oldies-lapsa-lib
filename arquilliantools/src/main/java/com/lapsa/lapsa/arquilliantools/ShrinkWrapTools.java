package com.lapsa.lapsa.arquilliantools;

import java.io.File;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolvedArtifact;
import org.jboss.shrinkwrap.resolver.api.maven.PackagingType;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.jboss.shrinkwrap.resolver.api.maven.coordinate.MavenCoordinate;

public class ShrinkWrapTools {

    public static final ShrinkWrapTools getInstance() {
	return new ShrinkWrapTools();
    }

    private static PomEquippedResolveStage pomResolveStage;

    public static EnterpriseArchive createEAR(String archiveName) {
	return ShrinkWrap.create(EnterpriseArchive.class, archiveName);
    }

    public static JavaArchive createEJB(String archiveName) {
	return createJAR(archiveName);
    }

    public static JavaArchive createJAR(String archiveName) {
	return ShrinkWrap.create(JavaArchive.class, archiveName);
    }

    public static void earAddDependencyArtifact(EnterpriseArchive ear, String artifactCanonicalForm) {
	initPomResolveStage();
	MavenResolvedArtifact[] arts = pomResolveStage
		.resolve(artifactCanonicalForm)
		.withTransitivity()
		.asResolvedArtifact();
	for (MavenResolvedArtifact artifact : arts)
	    jarAddMavenResolvedArtifact(ear, artifact);
    }

    public static void earAddRuntimeDependencies(EnterpriseArchive ear) {
	initPomResolveStage();
	MavenResolvedArtifact[] artifacts = pomResolveStage
		.importCompileAndRuntimeDependencies()
		.resolve()
		.withTransitivity()
		.asResolvedArtifact();
	for (MavenResolvedArtifact artifact : artifacts)
	    jarAddMavenResolvedArtifact(ear, artifact);
    }

    public static void jarAddManifestFolderRecursive(JavaArchive jar) {
	jarAddResources(jar, new File("src/main/resources/META-INF"), "/", true, false);
    }

    public static void jarAddAsResroucesRecursive(JavaArchive jar, File root, String target) {
	jarAddResources(jar, root, target, true, true);
    }

    public static void jarAddAsResroucesRecursive(JavaArchive jar, File root) {
	jarAddResources(jar, root, "/", true, true);
    }

    public static void jarAddAsResroucesNonRecursive(JavaArchive jar, File root, String target) {
	jarAddResources(jar, root, target, false, true);
    }

    public static void jarAddAsResroucesNonRecursive(JavaArchive jar, File root) {
	jarAddResources(jar, root, "/", false, true);
    }

    // G:A:P:C:V

    public static String generateCanonicalForm(String groupId, String artifactId, String packagingType) {
	return String.format("%1$s:%2$s:%3$s:?", groupId, artifactId, packagingType);
    }

    // PRIVATE

    private static void jarAddMavenResolvedArtifact(EnterpriseArchive ear, MavenResolvedArtifact artifact) {
	MavenCoordinate co = artifact.getCoordinate();
	if (co.getType().equals(PackagingType.JAR)) {
	    JavaArchive archive = ShrinkWrap.create(JavaArchive.class, co.getArtifactId() + ".jar");
	    archive.merge(artifact.as(JavaArchive.class));
	    ear.addAsLibrary(archive);
	}
	if (co.getType().equals(PackagingType.EJB)) {
	    JavaArchive archive = ShrinkWrap.create(JavaArchive.class, co.getArtifactId() + ".jar");
	    archive.merge(artifact.as(JavaArchive.class));
	    ear.addAsModule(archive);
	}
	if (co.getType().equals(PackagingType.WAR)) {
	    WebArchive archive = ShrinkWrap.create(WebArchive.class, co.getArtifactId() + ".war");
	    archive.merge(artifact.as(JavaArchive.class));
	    ear.addAsModule(archive);
	}
    }

    private static void jarAddResources(JavaArchive jar, File file, String targetPath, boolean recursive,
	    boolean resourceOrManifest) {
	if (file == null)
	    throw new NullPointerException();
	if (!file.exists() || !file.isDirectory())
	    throw new RuntimeException(String.format("%1$s must be a directory", file));
	String sub = (targetPath.startsWith("/") ? "" : "/") + targetPath + (targetPath.endsWith("/") ? "" : "/");
	for (File f : file.listFiles())
	    if (f.isFile()) {
		if (resourceOrManifest)
		    jar.addAsResource(f, sub + f.getName());
		else
		    jar.addAsManifestResource(f, sub + f.getName());
	    } else if (f.isDirectory() && recursive)
		jarAddResources(jar, f, sub + f.getName() + "/", recursive, resourceOrManifest);
    }

    private static void initPomResolveStage() {
	if (pomResolveStage == null)
	    pomResolveStage = Maven
		    .resolver()
		    .loadPomFromFile("pom.xml");
    }

}
