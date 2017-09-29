package andrealaforgia.com.androidcamerastreaming;

import android.hardware.Camera;

public interface CameraProvider {
    Camera getCamera() throws CameraInUseException;
}
