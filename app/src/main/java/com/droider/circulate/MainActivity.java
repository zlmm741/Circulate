package com.droider.circulate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 迭代器循环
        iterator();

        // for 循环
        forCirculate();

        // while 循环
        whileCirculate();

        // dowhile 循环
        dowhileCirculate();
    }

    // 获取正在运行的进程列表
    private void iterator() {
        // 管理应用程序的系统状态
        ActivityManager activityManager =
                (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> psInfos =
                activityManager.getRunningAppProcesses();
        StringBuilder sb = new StringBuilder();
        for (RunningAppProcessInfo info: psInfos) {
            sb.append(info.processName + "\n");
        }
        Toast.makeText(this,
                sb.toString(),
                Toast.LENGTH_SHORT).show();
    }

    // 获取已安装的程序
    private void forCirculate() {
        PackageManager pm = getApplicationContext().getPackageManager();
        // 返回所有的 ApplicationInfo
        List<ApplicationInfo> appInfos = pm.getInstalledApplications(
                PackageManager.MATCH_UNINSTALLED_PACKAGES);
        int size = appInfos.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; ++i) {
            ApplicationInfo info = appInfos.get(i);
            sb.append(info.packageName + "\n");
        }
        Toast.makeText(this,
                sb.toString(),
                Toast.LENGTH_SHORT).show();
    }

    // 获取正在运行的任务列表
    private void whileCirculate() {
        ActivityManager activityManager =
                (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        // getRunningTasks() 已作废，仅低版本可用
        List<RunningTaskInfo> taskInfos =
                activityManager.getRunningTasks(100);
        StringBuilder sb = new StringBuilder();
        Iterator<RunningTaskInfo> iterator = taskInfos.iterator();
        while (iterator.hasNext()) {
            RunningTaskInfo info = iterator.next();
            sb.append(info.toString() + "\n");
        }
        Toast.makeText(this,
                sb.toString(),
                Toast.LENGTH_SHORT).show();
    }

    // 获取正在动迁的服务列表
    private void dowhileCirculate() {
        ActivityManager activityManager =
                (ActivityManager)getSystemService(ACTIVITY_SERVICE);
        // getRunningServices() 已作废
        List<RunningServiceInfo> serviceInfos =
                activityManager.getRunningServices(100);
        StringBuilder sb = new StringBuilder();
        Iterator<RunningServiceInfo> iterator = serviceInfos.iterator();
        do {
            RunningServiceInfo info = iterator.next();
            sb.append(info.toString() + "\n");
        } while (iterator.hasNext());
        Toast.makeText(this,
                sb.toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
