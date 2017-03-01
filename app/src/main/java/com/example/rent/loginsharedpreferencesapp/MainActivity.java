package com.example.rent.loginsharedpreferencesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_firstName_textView)
    TextView firstNameTextView;
    @BindView(R.id.activity_main_lastName_textView)
    TextView lastNameTextView;
    @BindView(R.id.activity_main_login_textView)
    TextView loginTextView;
    @BindView(R.id.activity_main_country_textView)
    TextView countryTextView;
    @BindView(R.id.activity_main_language_textView)
    TextView languageTextView;
    @BindView(R.id.activity_main_gender_textView)
    TextView genderTextView;
    @BindView(R.id.activity_main_dateOfBirth_textView)
    TextView dateOfBirthTextView;

    Account account;
    AccountsManager accountsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadAccount();

        ButterKnife.bind(this);

        loadAccountDataIntoViews();
    }

    private void loadAccount(){
        String login = getIntent().getStringExtra(LoginActivity.LOGIN);
        accountsManager = new AccountsManager(this,login);
        try {
            account = accountsManager.getAccount();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAccountDataIntoViews(){
        loginTextView.setText(account.getLogin());
        firstNameTextView.setText(account.getFirstName());
        lastNameTextView.setText(account.getLastName());
        countryTextView.setText(account.getCountry());
        genderTextView.setText(account.getGender());
        countryTextView.setText(account.getCountry());
        dateOfBirthTextView.setText(account.getDateOfBirth());
        languageTextView.setText(account.getLanguage());
    }


}
