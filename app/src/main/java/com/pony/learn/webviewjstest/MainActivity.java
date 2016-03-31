package com.pony.learn.webviewjstest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	private Button callBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
	}

	@SuppressLint({"setJavaScriptEnabled", "AddJavascriptInterface"})
	private void initView() {
		final WebView webView = (WebView) findViewById(R.id.main_web_view);
		if (webView == null) {
			return;
		}
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/main.html");

		callBtn = (Button) findViewById(R.id.main_btn);
		callBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = "Android";
				webView.loadUrl("javascript:showJSName('" + name + "')");
			}
		});

		webView.addJavascriptInterface(new WebAppInterface(this), "Android");
	}


	public class WebAppInterface {
		Context mContext;

		/** Instantiate the interface and set the context */
		WebAppInterface(Context c) {
			mContext = c;
		}

		/** Show a toast from the web page */
		@JavascriptInterface
		public void showToast(String toast) {
			Toast.makeText(mContext, "Hello," + toast, Toast.LENGTH_SHORT).show();
		}
	}

}
