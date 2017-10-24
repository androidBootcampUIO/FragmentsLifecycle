package uio.androidbootcamp.fragmentslifecycle;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        FirstFragment.OnFirstFragmentInteractionListener {

    private TextView lifecycleLogTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirstFragment firstFragment = FirstFragment.newInstance("Some parameter");
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, firstFragment);
        fragmentTransaction.commit();

        lifecycleLogTextView = (TextView) findViewById(R.id.text_view_lifecycle_log_activity);
    }

    @Override
    public void onFirstFragmentEventLogged(String newLog) {
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
