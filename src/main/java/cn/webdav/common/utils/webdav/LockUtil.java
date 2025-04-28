package cn.webdav.common.utils.webdav;

import cn.webdav.common.constant.LockConstant;
import cn.webdav.pojo.entity.DAVLock;
import cn.webdav.pojo.webdav.lock.*;

import java.util.Objects;

public class LockUtil {
    public static LockDiscovery buildLockDiscovery(ActiveLock activeLock) {
        return LockDiscovery.builder().activeLock(activeLock).build();
    }

    public static SupportedLock buildSupportedLock(LockEntry lockEntry){
        return SupportedLock.builder().lockentry(lockEntry).build();
    }

    public static SupportedLock buildSupportedLock(DAVLock davLock){
        return buildSupportedLock(
                buildLockEntry(
                        buildLockScope(davLock.getLockScope()),
                        buildLockType(davLock.getLockType())
                ));
    }

    public static LockEntry buildLockEntry(LockScope lockScope, LockType lockType){
        return LockEntry.builder().lockscope(lockScope).locktype(lockType).build();
    }

    public static LockScope buildLockScope(String scope){
        if (LockConstant.LOCK_SCOPE_EXCLUSIVE.equals(scope)) {
            return LockScope.Exclusive();
        }else {
            return LockScope.Shared();
        }
    }

    public static LockType buildLockType(String type){
        return LockType.Write();
    }
}
