package com.example.rent.loginsharedpreferencesapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Date;

/**
 * Created by RENT on 2017-02-15.
 */

public class AccountsManager {


    public static final String PREFERENCES_ALL_ACCOUNTS = "all accounts";
    public static final String PREFERENCE_FIRST_NAME = "key first name";
    public static final String PREFERENCE_LAST_NAME="key last name";
    public static final String PREFERENCE_COUNTRY="key country";
    public static final String PREFERENCE_LANGUAGE="key language";
    public static final String PREFERENCE_LOGIN="key login";
    public static final String PREFERENCE_PASSWORD="key password";
    public static final String PREFERENCE_GENDER="key gender";
    public static final String PREFERENCE_DATE_OF_BIRTH="key dateOfBirth";
    //public static final String PREFERENCE_SINGLE_TEST="this is test preference";



    public final static String TAG = "ACCOUNTS_MANAGER";

    SharedPreferences allAccountsPreferences;
    SharedPreferences accountPreferences;
    Context context;

    public AccountsManager(Context context,String accountPreferencesName) {

        this.context = context;
        allAccountsPreferences = context.getSharedPreferences(PREFERENCES_ALL_ACCOUNTS,Context.MODE_PRIVATE);
        accountPreferences = context.getSharedPreferences(accountPreferencesName,Context.MODE_PRIVATE);

    }

    public void setAccountPreferenceName(String accountPreferencesName){

        accountPreferences = context.getSharedPreferences(accountPreferencesName,Context.MODE_PRIVATE);
    }

    public boolean isPasswordCorrect(String password){

       return password.equals(accountPreferences.getString(PREFERENCE_PASSWORD,"")) ;

    }

    private boolean loginExists(String login){
        Log.d(TAG, "loginExists: "+!allAccountsPreferences.getString(login,"").isEmpty());
        return (!allAccountsPreferences.getString(login,"").isEmpty());
    }

    public void saveAccount(Account account) throws Exception {

        if(loginExists(account.getLogin())) throw new Exception("Login already exists!");

        accountPreferences = context.getSharedPreferences(account.getLogin(),Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = accountPreferences.edit();
        editor.putString(PREFERENCE_FIRST_NAME,account.getFirstName());
        editor.putString(PREFERENCE_LAST_NAME,account.getLastName());
        editor.putString(PREFERENCE_COUNTRY,account.getCountry());
        editor.putString(PREFERENCE_LANGUAGE,account.getLanguage());
        editor.putString(PREFERENCE_LOGIN,account.getLogin());
        editor.putString(PREFERENCE_PASSWORD,account.getPassword());
        editor.putString(PREFERENCE_DATE_OF_BIRTH,account.getDateOfBirth());
        editor.putString(PREFERENCE_GENDER,account.getGender());

        addAccountToAllAccounts(account);

        editor.apply();
    }

    public void updateField(String preferenceName,String newValue){

        SharedPreferences.Editor editor = accountPreferences.edit();
        editor.putString(preferenceName,newValue);

    }
    private void addAccountToAllAccounts(Account account) {

        SharedPreferences.Editor editor = allAccountsPreferences.edit();

        Log.d(TAG, "addAccountToAllAccounts: ");
        editor.putString(account.getLogin(),account.getPassword());
        editor.apply();
    }

    public Account getAccount() throws Exception {

        String firstName = accountPreferences.getString(PREFERENCE_FIRST_NAME,"");
        String lastName = accountPreferences.getString(PREFERENCE_LAST_NAME,"");
        String country = accountPreferences.getString(PREFERENCE_COUNTRY,"");
        String language = accountPreferences.getString(PREFERENCE_LANGUAGE,"");
        String login = accountPreferences.getString(PREFERENCE_LOGIN,"");
        String password = accountPreferences.getString(PREFERENCE_PASSWORD,"");
        String dateOfBirth = accountPreferences.getString(PREFERENCE_DATE_OF_BIRTH,"");
        String gender = accountPreferences.getString(PREFERENCE_GENDER,"");

        return new Account(firstName,lastName,country,language,login,password,gender,dateOfBirth);
    }

}
