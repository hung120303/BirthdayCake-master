package cs301.birthdaycake;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;


public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, View.OnTouchListener {
    private CakeView cakeView;

    private CakeModel cakeModel;

    public CakeController(CakeView cake){
        //set equal to reference to a CakeView object
        cakeView = cake;

        cakeModel = cakeView.getCakeModel();

    }

    @Override
    public void onClick(View view) {
        Log.d("click", "idk");
        cakeModel.isLit = false;
        cakeView.invalidate();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        cakeModel.hasCandles = b;
        cakeView.invalidate();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        cakeModel.x =  motionEvent.getX();
        cakeModel.y =  motionEvent.getY();
        cakeView.invalidate();
        return false;
    }
}
