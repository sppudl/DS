class TokenRing {
    private boolean hasToken = false;

    public synchronized void requestToken() {
        while (!hasToken) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized void giveToken() {
        hasToken = true;
        notify();
    }

    public synchronized void passToken() {
        hasToken = false;
        notify();
    }
}
