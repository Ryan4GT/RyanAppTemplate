package com.ryan.ryanapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ryan.ryanapp.R;
import com.ryan.ryanapp.Utils.StringUtil;
import com.ryan.ryanapp.leancloud.LeanCloudUtils;
import com.ryan.ryanapp.leancloud.LoginOrSignupResultListener;

import java.util.Iterator;
import java.util.Map;

public class FragmentLogin extends FragmentBase {

    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    /**
     * Use this factory method to create a new instance of this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentBase.
     */
    public static FragmentLogin newInstance(Map<String, String> params) {
        FragmentLogin fragmentLogin = new FragmentLogin();
        Bundle args = new Bundle();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            args.putString(key, params.get(key));
        }
        fragmentLogin.setArguments(args);
        return fragmentLogin;
    }

    public FragmentLogin() {}

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        toolbar.setTitle("Login");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentRootView = inflater.inflate(R.layout.fragment_login, container, false);

        mEmailView = (EditText) fragmentRootView.findViewById(R.id.email);
        mPasswordView = (EditText) fragmentRootView.findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if(id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        Button mEmailSignInButton = (Button) fragmentRootView.findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = fragmentRootView.findViewById(R.id.login_form);
        mProgressView = fragmentRootView.findViewById(R.id.login_progress);
        mProgressView.setVisibility(View.GONE);
        return fragmentRootView;
    }

    public void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);
        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if(!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if(StringUtil.isEmpty(email) || email.length() < 3) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if(StringUtil.isEmpty(password)) {
            mEmailView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if(cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            LeanCloudUtils.login(email, password, new LoginOrSignupResultListener() {
                @Override public void onLoginOrSignupResult(boolean succeed, String result) {
                    if(succeed) {
                        getActivity().finish();
                    }else{
                        if(result.equals("211")){
                            LeanCloudUtils.signup(email, password, new LoginOrSignupResultListener() {
                                @Override public void onLoginOrSignupResult(boolean succeed, String result) {
                                    if(succeed){
                                        getActivity().finish();
                                    }else{
                                        Toast.makeText(getActivity(), result + " : Signup failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(getActivity(), result + " : Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    mProgressView.setVisibility(View.GONE);
                }
            });
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public void showProgress(final boolean show) {

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
//        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }
}
