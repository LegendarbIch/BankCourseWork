package auth;

public class UserAuth extends Thread{
    private boolean isAuth ;

    public boolean isAuth() {
        return isAuth;
    }
    public void auth() {
        synchronized (this) {
            this.start();
            setAuth(true);
        }
    }
    public void logOut() {
        synchronized (this) {
            this.interrupt();
            setAuth(false);
        }
    }
    private void setAuth(boolean auth) {
        isAuth = auth;
    }
}
