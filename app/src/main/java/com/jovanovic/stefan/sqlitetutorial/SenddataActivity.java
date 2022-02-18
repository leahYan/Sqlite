package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class SenddataActivity extends AppCompatActivity {

    private EditText eMsg;
    private Button btn;
    CustomAdapter customAdapter;
    MyDatabaseHelper myDB;  //initialise class object
    SQLiteDatabase sqLiteDatabase;
    File attachment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senddata);

        myDB = new MyDatabaseHelper(SenddataActivity.this);
        eMsg = findViewById(R.id.txtMsg);
        btn = findViewById(R.id.btnSend);
        btn.setOnClickListener(new View.OnClickListener() {
//?
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_SEND);

                sqLiteDatabase = myDB.getReadableDatabase();
                exportDatabse("Sample.db");
                //copyFileToExternal("Sample.db");
                sendEmail("RTAData@Riotinto.com");
            /*
                            File root = Environment.getExternalStorageDirectory();
                            String fileName = "Sample.db";
                            if (root.canWrite()) {
                                attachment = new File(root, fileName);
                            }

                            it.setData(Uri.parse("mailto:")); // only email apps should handle this
                            it.putExtra(Intent.EXTRA_EMAIL, new String[]{"RTAData@Riotinto.com"});
                            it.putExtra(Intent.EXTRA_SUBJECT,"Test email");
                            it.putExtra(Intent.EXTRA_TEXT,eMsg.getText());
                            //it.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(sqLiteDatabase));
                            it.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(attachment));

                            //it.setType("message/rfc822");
                            startActivity(Intent.createChooser(it,"Choose Mail App"));
*/

            }
        });
    }
    public void exportDatabse(String databaseName) {

        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//" + getPackageName() + "//databases//" +databaseName+ "";
                String backupDBPath = "backupname.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }else{
                    System.out.println(":::");
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    private  File copyFileToExternal(String fileName) {
        File file = null;
        String newPath = Environment.getExternalStorageState();

        try {
            File f = new File(newPath);
            f.mkdirs();
            FileInputStream fin = openFileInput(fileName);
            FileOutputStream fos = new FileOutputStream(newPath + fileName);
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = fin.read(buffer)) != -1) {
                fos.write(buffer, 0, len1);
            }
            fin.close();
            fos.close();
            file = new File(newPath + fileName);
            if (file.exists())
                return file;
        } catch (Exception e) {

        }
        return null;
    }

    private void sendEmail(String email) {

        File file = new File(Environment.getExternalStorageState()+"/Download/" + "Sample.db");
        Uri path = Uri.fromFile(file);
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("application/octet-stream");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Test email");
        String to[] = { email };
        intent.putExtra(Intent.EXTRA_EMAIL, to);
        intent.putExtra(Intent.EXTRA_TEXT, Environment.getExternalStorageState());
        intent.putExtra(Intent.EXTRA_STREAM, path);
        startActivityForResult(Intent.createChooser(intent, "Send mail..."),
                1222);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1222) {
            File file = new File(Environment.getExternalStorageState()+"/Download/" +"Sample.db");
            file.delete();
        }
    }
}

/*
// Gets the current date
    System.currentTimeMillis();
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    Date date = new Date(System.currentTimeMillis());

*/