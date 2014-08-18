package com.s212037943.kurvinhendricks.addressbookapplication.views;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.s212037943.kurvinhendricks.addressbookapplication.R;
import com.s212037943.kurvinhendricks.addressbookapplication.domain.Contact;
import com.s212037943.kurvinhendricks.addressbookapplication.repository.DataSourceDAO;
import com.s212037943.kurvinhendricks.addressbookapplication.repository.Impl.DataSourceDAOImpl;


public class MainActivity extends Activity {

    private Button pressMe;
    private DataSourceDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pressMe = (Button) findViewById(R.id.press_me_button);
        dao = new DataSourceDAOImpl(this);

        pressMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact = new Contact.Builder("0735141397")
                        .setEmailAddress("kurvinhendricks@gmail.com")
                        .setfName("Kurvin")
                        .setlName("Hendricks")
                        .setHomeAddress("24 Madison Drive")
                        .build();

                dao.createContact(contact);

                Toast.makeText(getApplicationContext(), contact.getfName() + " " + contact.getCellNumber(), Toast.LENGTH_LONG).show();

                contact = dao.findContactByID(1);
                int size = dao.getCursor();
                
                Toast.makeText(getApplicationContext(), size + " size", Toast.LENGTH_LONG ).show();
                Toast.makeText(getApplicationContext(), contact.getId() + " " + contact.getlName() + " " + contact.getCellNumber(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
