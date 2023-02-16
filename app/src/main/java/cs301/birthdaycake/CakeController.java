package cs301.birthdaycake;

import android.util.Log;
import android.view.View;

public class CakeController implements View.OnClickListener {
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
}
