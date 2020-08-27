/*
 * olgapetrova-git/mau_mau
 */
package htwberlin.mau_mau.view_management.view;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Scanner;

/*
 * Provides an entry point to start a program.
  */
public class Main {

	private static Class<?> cls = null;
	// get the platform string
	public static String systemName = System.getProperty("os.name").toLowerCase();

	public static void main(String[] args) throws Exception {
		cls = Class.forName("htwberlin.mau_mau.view_management.view.UI");

		// check if an argument was passed on jar execution
		if (args.length == 0) {
			// get the path of the currently running jar
			final String jarPath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			final String decodedPath = URLDecoder.decode(jarPath, "UTF-8");
			// Setting for the terminal window title (Linux/Windows)
			// final String windowTitle = "UI Name";
			final String windowTitle = cls.getSimpleName();
			// Check the current platform...
			if (systemName.contains("windows")) {
				// then start the new process with the OS or terminal dependent commands
				ProcessBuilder processBuilder = new ProcessBuilder(new String[] { "cmd", "/k", "start",
						"\"" + windowTitle + "\"", "java", "-jar", decodedPath.substring(1), "run" });
				processBuilder.start();
			} else if (systemName.contains("mac")) {
				new ProcessBuilder(new String[] { "/bin/bash", "-c", "java", "-jar", decodedPath, "run" }).start();
			} else if (systemName.contains("linux")) {
				// TODO: add support for other Linux terminals
				new ProcessBuilder(new String[] { "xfce4-terminal", "--title=" + windowTitle, "--hold", "-x", "java",
						"-jar", decodedPath, "run" }).start();
			} else {
				// No OS could be detected
				System.err.println("OS could not be detected.");
			}
			// destroy the original process
			System.exit(0);
		} else {
			// ACTUAL PROGRAM TO EXECUTE COMES HERE
			Method meth = cls.getMethod("main", String[].class);
			String[] params = null; // init params accordingly
			meth.invoke(null, (Object) params); // static method doesn't have an instance

			waitForEnter();
		}
	}

	// https://stackoverflow.com/questions/6032118/make-the-console-wait-for-a-user-input-to-close
	private static void waitForEnter() {
		try (Scanner s = new Scanner(System.in)) {
			System.out.println("Press enter to continue.....");
			s.nextLine();
		}
	}

	@SuppressWarnings("unused")
	private static void waitForEnter2() throws InterruptedException, IOException {
		new ProcessBuilder("cmd", "/c", "pause > null").inheritIO().start().waitFor();
	}
}
