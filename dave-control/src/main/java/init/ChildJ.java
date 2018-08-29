package init;

/**
 * @author chen.z
 * @date 2018/7/25
 */
public class ChildJ extends FatherJ {

    public ChildJ(){
        System.out.println("ChildJ constructor!");
    }

    private static Integer childNum;

//    private static Integer childNum2 = initNumber();

    final Integer childFinalNum = initChildFinalNum();

    private static Integer initNumber() {
        System.out.println("ChildJ childNum2 initNumber!");
        return 10000;
    }

    private Integer initChildFinalNum() {
        System.out.println("FatherJ fatherFinalNum initFatherFinalNum!");
        return 111;
    }

    static {
        System.out.println("ChildJ static code area!");
    }

}
