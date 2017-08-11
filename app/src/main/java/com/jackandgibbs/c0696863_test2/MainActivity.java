package com.jackandgibbs.c0696863_test2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity
{
    EditText txtEmployeeID, txtEmployeeName, txtSalary;

    TextView txtBirthdate;

    String EmployeeID, EmployeeName, Salary, Birthdate;

    TextInputLayout tilEID, tilENAME, tilSALARY;

    Button btnRegister;

    Realm realm;

    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Register");

        myCalendar = Calendar.getInstance();

        txtEmployeeID = (EditText) findViewById(R.id.txtEmployeeID);
        txtEmployeeName = (EditText) findViewById(R.id.txtEmployeeName);
        txtSalary = (EditText) findViewById(R.id.txtSalary);

        txtBirthdate = (TextView) findViewById(R.id.txtBirthDate);

        tilEID = (TextInputLayout) findViewById(R.id.tilEID);
        tilENAME = (TextInputLayout) findViewById(R.id.tilENAME);
        tilSALARY = (TextInputLayout) findViewById(R.id.tilSALARY);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!validate())
                {
                    return;
                }

                Realm.init(getApplicationContext());
                realm=Realm.getDefaultInstance();
                EmployeeData obj=new EmployeeData();
                obj.employeeID=txtEmployeeID.getText().toString();
                obj.employeeName=txtEmployeeName.getText().toString();
                obj.employeeBirthDate=txtBirthdate.getText().toString();
                obj.employeeSalary=txtSalary.getText().toString();
                realm.beginTransaction();
                realm.copyToRealm(obj);
                realm.commitTransaction();
                Toast.makeText(MainActivity.this, "Employee record saved!!!", Toast.LENGTH_SHORT).show();

                txtEmployeeID.setText("");
                txtEmployeeName.setText("");
                txtBirthdate.setText("Birth Date");
                txtSalary.setText("");
            }
        });

        txtBirthdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                new DatePickerDialog(MainActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public boolean validate()
    {
        boolean valid = true;

        EmployeeID = txtEmployeeID.getText().toString();
        EmployeeName = txtEmployeeName.getText().toString();
        Salary = txtSalary.getText().toString();
        Birthdate = txtBirthdate.getText().toString();

        if (EmployeeID.isEmpty())
        {
            tilEID.setError("Enter employee id");
            valid = false;
        }
        else
        {
            tilEID.setError(null);
        }

        if (EmployeeName.isEmpty())
        {
            tilENAME.setError("Enter employee name");
            valid = false;
        }
        else
        {
            tilENAME.setError(null);
        }

        if (Salary.isEmpty())
        {
            tilSALARY.setError("Enter employee salary");
            valid = false;
        }
        else
        {
            tilSALARY.setError(null);
        }

        return valid;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.add)
        {
            startActivity(new Intent(MainActivity.this, EmployeeList.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            txtBirthdate.setText((monthOfYear+1) + "/" + dayOfMonth + "/" + year);
        }

    };
}