package andrealaforgia.com.androidcamerastreaming;

import android.hardware.Camera;

public class CameraManager implements CameraProvider {

    enum State {
        OPENED,
        RELEASED
    }

    private Camera camera;
    private State state = State.RELEASED;

    public synchronized void release() {
        if (state == State.RELEASED) return;
        camera.release();
        state = State.RELEASED;
    }

    @Override
    public synchronized Camera getCamera() throws CameraInUseException {
        if (state == State.RELEASED) {
            try {
                camera = Camera.open();
                state = State.OPENED;
            } catch (Exception e) {
                throw new CameraInUseException();
            }
        }
        return camera;
    }

    public void lock() throws CameraInUseException {
        getCamera().lock();
    }

    public void unlock() throws CameraInUseException {
        getCamera().unlock();
    }
}
