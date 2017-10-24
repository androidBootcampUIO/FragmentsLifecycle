package uio.androidbootcamp.fragmentslifecycle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements
        FirstFragment.OnFirstFragmentInteractionListener,
        SecondFragment.OnSecondFragmentInteractionListener {

    private TextView lifecycleLogTextView;

    private boolean isFirstFragmentSelected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirstFragment firstFragment = FirstFragment.newInstance("first: " + getRandomInt());
        showFragment(firstFragment, null);

        lifecycleLogTextView = (TextView) findViewById(R.id.text_view_lifecycle_log_activity);

        Button changeFragmentButton = (Button) findViewById(R.id.button_change_fragment);
        changeFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFirstFragmentSelected) {
                    SecondFragment secondFragment = SecondFragment.newInstance("second: " + getRandomInt());
                    showFragment(secondFragment, "second");
                    isFirstFragmentSelected = false;
                } else {
                    FirstFragment firstFragment = FirstFragment.newInstance("first: " + getRandomInt());
                    showFragment(firstFragment, null);
                    isFirstFragmentSelected = true;
                }
            }
        });
    }

    private int getRandomInt() {
        Random rand = new Random();
        return rand.nextInt();
    }


    private void showFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        if (tag != null) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onFirstFragmentEventLogged(String newLog) {
        logLifecycleEvent(newLog);
    }

    @Override
    public void onSecondFragmentEventLogged(String newLog) {
        logLifecycleEvent(newLog);
    }

    private void logLifecycleEvent(String newLog) {
        String textViewExistsLog = " (el textView activity aun no existe)";
        if (lifecycleLogTextView != null) {
            textViewExistsLog = " (el textView activity ya existe)";
            String text = lifecycleLogTextView.getText().toString();
            text = newLog + textViewExistsLog + "\n---\n" + text;
            lifecycleLogTextView.setText(text);
        }
        Log.e("LIFECYCLE_DEMO_ACTIVITY", newLog + textViewExistsLog);
    }
}
