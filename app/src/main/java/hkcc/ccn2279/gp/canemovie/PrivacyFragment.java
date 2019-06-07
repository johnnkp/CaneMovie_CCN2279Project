package hkcc.ccn2279.gp.canemovie;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PrivacyFragment extends Fragment {
    public PrivacyFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.privacy, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // https://shareandopen.tumblr.com/post/24470630918/android-open-mult-lang-html-file-from-raw
        String myURL = "file:///android_res/raw/privacy.html";
        WebView myBrowser = (WebView) getView().findViewById(R.id.mybrowser);
        WebSettings websettings = myBrowser.getSettings();
        websettings.setSupportZoom(true);
        websettings.setBuiltInZoomControls(true);
        myBrowser.setWebViewClient(new WebViewClient());
        myBrowser.loadUrl(myURL);
        super.onActivityCreated(savedInstanceState);
    }
}
