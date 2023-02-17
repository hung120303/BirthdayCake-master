package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        CakeView cakeView = findViewById(R.id.cakeview);

        CakeController cakeController = new CakeController(cakeView);

        findViewById(R.id.button).setOnClickListener(cakeController);

        CompoundButton b = findViewById(R.id.switch2);
        b.setOnCheckedChangeListener(cakeController);
        cakeView.setOnTouchListener(cakeController);


    }

    public void goodbye(View button){
        Log.i("button", "Goodbye");
    }
}
