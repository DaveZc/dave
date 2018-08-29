package init;

/**
 * @author chen.z
 * @date 2018/7/25
 */
public class FatherJ {

    public FatherJ(){
        System.out.println("FatherJ constructor!");
    }

    private static Integer fatherNum;

//    private static Integer fatherNum2 = initNumber();

    final Integer fatherFinalNum = initFatherFinalNum();

    private static Integer initNumber() {
        System.out.println("FatherJ fatherNum2 initNumber!");
        return 100;
    }

    private Integer initFatherFinalNum() {
        System.out.println("FatherJ fatherFinalNum initFatherFinalNum!");
        return 111;
    }

    static {
        System.out.println("FatherJ static code area!");
    }

}
