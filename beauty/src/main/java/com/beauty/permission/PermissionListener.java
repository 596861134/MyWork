package com.beauty.permission;

import java.util.List;

/**
 * Created by donkor on 2016/12/27.
 */

public interface PermissionListener {
    void onGranted();

    void onDenied(List<String> deniedPermission);
}
