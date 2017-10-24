package uio.androidbootcamp.fragmentslifecycle;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    private OnSecondFragmentInteractionListener mListener;
    private TextView lifecycleLogTextView;

    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance(String param1) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logLifecycleStep("2: [SecondFragment] onCreate()");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        logLifecycleStep("3: [SecondFragment] onCreateView()");
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        TextView helloFirstFragmentTextView = view.findViewById(R.id.text_view_hello_first_fragment);
        lifecycleLogTextView = view.findViewById(R.id.text_view_lifecycle_log_fragment);
        helloFirstFragmentTextView.setText(getActivity().getString(R.string.hello_second_fragment, mParam1));
        return view;
    }

    public void logOnActivity(String newLog) {
        if (mListener != null) {
            mListener.onSecondFragmentEventLogged(newLog);
        }
    }

    private void logLifecycleStep(String newText) {
        String textViewExistsLog = " (el textView fragment aun no existe)";
        if (lifecycleLogTextView != null) {
            textViewExistsLog = " (el textView fragment ya existe)";
            String text = lifecycleLogTextView.getText().toString();
            text = newText + textViewExistsLog + "\n---\n" + text;
            lifecycleLogTextView.setText(text);
        }
        logOnActivity(newText + textViewExistsLog);
        Log.e("LIFECYCLE_FRAGMENT_2", newText + textViewExistsLog);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        logLifecycleStep("1: [SecondFragment] onAttach()");
        if (context instanceof OnSecondFragmentInteractionListener) {
            mListener = (OnSecondFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSecondFragmentInteractionListener");
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logLifecycleStep("4: [SecondFragment] onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        logLifecycleStep("5: [SecondFragment] onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        logLifecycleStep("6: [SecondFragment] onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        logLifecycleStep("7: [SecondFragment] onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        logLifecycleStep("8: [SecondFragment] onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        logLifecycleStep("9: [SecondFragment] onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logLifecycleStep("10: [SecondFragment] onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        logLifecycleStep("11: [SecondFragment] onDetach()");
    }

    public interface OnSecondFragmentInteractionListener {
        void onSecondFragmentEventLogged(String newLog);
    }
}
