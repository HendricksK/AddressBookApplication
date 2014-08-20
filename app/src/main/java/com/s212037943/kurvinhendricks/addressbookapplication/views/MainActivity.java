package com.s212037943.kurvinhendricks.addressbookapplication.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.s212037943.kurvinhendricks.addressbookapplication.R;
import com.s212037943.kurvinhendricks.addressbookapplication.domain.Contact;
import com.s212037943.kurvinhendricks.addressbookapplication.repository.DataSourceDAO;
import com.s212037943.kurvinhendricks.addressbookapplication.repository.Impl.DataSourceDAOImpl;


public class MainActivity extends Activity {

    private Button pressMe;
    private DataSourceDAO dao;

    EditText phone_number_edittext;
    EditText last_name_edittext;
    EditText first_name_edittext;
    EditText email_address_edittext;
    EditText home_address_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pressMe = (Button) findViewById(R.id.press_me_button);
        phone_number_edittext = (EditText) findViewById(R.id.phone_number_edittext);
        last_name_edittext = (EditText) findViewById(R.id.last_name_edittext);
        first_name_edittext = (EditText) findViewById(R.id.first_name_edittext);
        email_address_edittext = (EditText) findViewById(R.id.email_address_edittext);
        home_address_edittext = (EditText) findViewById(R.id.home_address_edittext);

        dao = new DataSourceDAOImpl(this);

        pressMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Log.i("button click", phone_number_edittext.getText().toString());

                Contact contact = new Contact.Builder(phone_number_edittext.getText().toString())
                        .setlName(last_name_edittext.getText().toString())
                        .setfName(first_name_edittext.getText().toString())
                        .setEmailAddress(email_address_edittext.getText().toString())
                        .setHomeAddress(home_address_edittext.getText().toString())
                        .build();

                dao.createContact(contact);

                //Toast.makeText(getApplicationContext(), contact.getfName() + " " + contact.getCellNumber(), Toast.LENGTH_LONG).show();


                int size = dao.getCursor();
                contact = dao.findContactByID(size);

                //Toast.makeText(getApplicationContext(), size + " size", Toast.LENGTH_LONG ).show();
                //Toast.makeText(getApplicationContext(), contact.getId() + " " + contact.getlName() + " " + contact.getCellNumber(), Toast.LENGTH_LONG).show();

                Intent detailIntent = new Intent(getApplicationContext(), DetailActivity.class);
                detailIntent.putExtra("ContactID", contact.getId());
                startActivity(detailIntent);
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
