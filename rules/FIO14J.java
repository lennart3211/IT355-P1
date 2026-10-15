/**
 * Rule: Perform proper cleanup before program termination
 * by closing database resources when they are no longer needed.
 *
 * @author: luke
 */
public void quit() {
    if (repo != null) {
        repo.close();
    }
}
