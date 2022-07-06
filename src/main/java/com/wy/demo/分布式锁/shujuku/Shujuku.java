/*
package com.wy.demo.分布式锁.shujuku;

public class Shujuku {
    // 定义一个map，用来做可重入，模仿zk的可重入实现。
    private final ConcurrentMap<Thread, LockData> threadData;
    */
/**
     * @param lockName 锁名称
     * @param lockTime 锁时间
     * @return
     *//*

    public boolean acquire(String lockName, Long lockTime) {
        Thread currentThread = Thread.currentThread();
        LockData lockData = threadData.get(currentThread);
        // 重入
        if (lockData != null) {
            lockData.lockCount.incrementAndGet();
            return true;
        }
        // 先检查是否被其他客户端锁上了
        LockRecordDTO lockRecord = lockRecordMapperExt.selectByLockName(lockName);
        if (lockRecord == null) {// 锁还不存在
            String lockOwner = generatorOwner();
            // 尝试获取锁
            boolean acquired = tryAcquireIfLockNotExist(lockName, lockTime, lockOwner);
            if (acquired) {// 获取到了
                startExtendExpireTimeTask(lockName, lockOwner, lockTime);
                lockData = new LockData(currentThread, lockName, lockOwner);
                // 放入map
                threadData.put(currentThread, lockData);
            }
            return acquired;
        }
        // 锁已经存在了，检查它的过期时间
        long lockExpireTime = lockRecord.getExpireTime();
        // 如果已经过期，那么再次争锁，只存在于上一次获取锁的线程没有正确释放锁时
        if (lockExpireTime < System.currentTimeMillis()) {
            // 当上一次获取锁的线程没有正确释放锁时，其他线程获取锁时会走到这里
            String lockOwner = generatorOwner();
            boolean acquired = tryAcquireIfLockExist(lockRecord, lockTime, lockOwner);
            if (acquired) {
                lockData = new LockData(currentThread, lockName, lockOwner);
                threadData.put(currentThread, lockData);
            }
            return acquired;
        }
        return false;
    }

    */
/**
     * 尝试获得锁，数据库表有设置唯一键约束，只有插入成功的线程才可以获取锁
     *
     * @param lockName  锁名称
     * @param lockTime  锁的过期时间
     * @param lockOwner 锁的拥有者
     * @return
     *//*

    private boolean tryAcquireIfLockNotExist(String lockName, long lockTime, String lockOwner) {
        try {
            LockRecordDTO lockRecord = new LockRecordDTO();
            lockRecord.setLockName(lockName);
            Long expireTime = System.currentTimeMillis() + lockTime;
            lockRecord.setExpireTime(expireTime);
            lockRecord.setLockOwner(lockOwner);
            lockRecord.setVersion(0);
            int insertCount = lockRecordMapperExt.insert(lockRecord);
            return insertCount == 1;
        } catch (Exception exp) {
            return false;
        }
    }

    */
/**
     * 当上一次获取锁的线程没有正确释放锁时，下一次其他线程获取锁时会调用本方法
     * 这时候也不用删除锁了，直接再利用就好，此时就是用乐观锁来控制互斥性了
     * 当多个线程竞争获取锁时，有乐观锁控制，只有更新成功的线程才会获的锁
     *
     * @param lockRecord 锁记录，里面保存了上一次获取锁的拥有者信息
     * @param lockTime   锁过期时间
     * @param lockOwner  锁的拥有者
     * @return
     *//*

    private boolean tryAcquireIfLockExist(LockRecordDTO lockRecord, long lockTime, String lockOwner) {
        try {
            // 获取锁时，如果数据库中有记录且超时时间小于当前时间，说明持有锁的客户端崩溃退出了，
            // 没有正确释放锁，才会导致表中有过期的记录。
            // 这时，并发的获取锁时，只有更新成功的线程才可以获取锁。
            Long expireTime = System.currentTimeMillis() + lockTime;
            lockRecord.setExpireTime(expireTime);
            lockRecord.setLockOwner(lockOwner);
            int updateCount = lockRecordMapperExt.updateExpireTime(lockRecord);
            return updateCount == 1;
        } catch (Exception exp) {
            return false;
        }
    }
    对应乐观锁更新 sql 如下：

<update id="updateExpireTime" parameterType="com.iwill.db.model.LockRecordDTO">
    update lock_record
    set expire_time = #{expireTime},
    version = version + 1
    where lock_name = #{lockName} and version = #{version}
</update>
1.3.2 释放锁逻辑
释放锁时，只有持有锁的线程才可以释放锁，代码如下：

/**
* 释放锁
* 实现参考zookeeper的锁释放机制
*/
/*
public void release() {
        Thread currentThread = Thread.currentThread();
        LockData lockData = threadData.get(currentThread);
        if (lockData == null) {
        throw new RuntimeException("current thread do not own lock");
        }
        int newLockCount = lockData.lockCount.decrementAndGet();
        if (newLockCount > 0) {
        return;
        }
        if (newLockCount < 0) {
        throw new RuntimeException("Lock count has gone negative for lock :" + lockData.lockName);
        }
        try {
        lockRecordMapperExt.deleteByOwner(lockData.lockName, lockData.owner);
        } finally {
        threadData.remove(currentThread);
        }
        }
        对应的底层 sql 如下：

<delete id="deleteByOwner" parameterType="java.util.Map">
        delete from lock_record where lock_name = #{lockName} and lock_owner = #{lockOwner}
</delete>
}
*/
