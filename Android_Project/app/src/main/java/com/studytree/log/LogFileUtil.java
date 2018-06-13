package com.studytree.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;


/**
 * [一句话功能简述] 日志文件管理工具
 * [功能详细描述]
 *
 */
public class LogFileUtil
{
    private static final String TAG = "FileUtil";
    
    private LogFileUtil()
    {
    }
    
    /**
     * fore delete a file,thread safe.
     * @param file file
     * @return del result
     */
    public static boolean forceDeleteFile(File file)
    {
        boolean result = false;
        int tryCount = 0;
        while (!result && tryCount++ < 10)
        {
            result = file.delete();
            if (!result)
            {
                try
                {
                    synchronized (file)
                    {
                        file.wait(200);
                    }
                }
                catch (InterruptedException e)
                {
                	Log.e("FileUtil.forceDeleteFile", "", e);
                }
            }
        }
        Log.v("FileUtil.forceDeleteFile", "tryCount = " + tryCount);
        return result;
    }
    
    /**
     * read strings for a file  in /data/data/package/filename
     * @param context context
     * @param file file
     * @return strings for a file in /data/data/package/filename
     */
    public static String read(Context context, String file)
    {
        String data = "";
        try
        {
            FileInputStream stream = context.openFileInput(file);
            StringBuffer sb = new StringBuffer();
            int c;
            while ((c = stream.read()) != -1)
            {
                sb.append((char) c);
            }
            stream.close();
            data = sb.toString();
            
        }
        catch (FileNotFoundException e)
        {
        	Log.e(TAG, e.getMessage());
        }
        catch (IOException e)
        {
        	Log.e(TAG, e.getMessage());
        }
        return data;
    }
    
    /**
     * write strings to a file  in /data/data/package/filename
     * @param context context
     * @param file file
     * @param msg msg
     */
    @SuppressLint("WorldWriteableFiles")
	public static void write(Context context, String file, String msg)
    {
        try
        {
            @SuppressWarnings("deprecation")
			FileOutputStream stream = context.openFileOutput(file,
                    Context.MODE_WORLD_WRITEABLE);
            stream.write(msg.getBytes());
            stream.flush();
            stream.close();
        }
        catch (FileNotFoundException e)
        {
        	Log.e(TAG, e.getMessage());
        }
        catch (IOException e)
        {
        	Log.e(TAG, e.getMessage());
        }
    }
    


}

