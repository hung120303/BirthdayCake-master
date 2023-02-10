package cs301.birthdaycake;

public class CakeController {
    private CakeView cakeView;

    private CakeModel cakeModel;

    public CakeController(CakeView cake){
        //set equal to reference to a CakeView object
        cakeView = cake;

        cakeModel = cakeView.getCakeModel();

    }


}
