package reflect;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.*;

/**
 * @author chen.z
 * @date 2018/7/23
 */
public class PrintUtils {

    public static String printClassInfo(Object obj) {

        //print class-head
        StringBuilder resultBuilder = new StringBuilder(64);

        Class clazz = obj.getClass();

        String clazzName = clazz.getName();

        String clazzModifiers = Modifier.toString(clazz.getModifiers());

        Class superClazz = clazz.getSuperclass();

        if (StringUtils.isNotBlank(clazzModifiers)) {
            resultBuilder.append(clazzModifiers).append(" ");
        }

        resultBuilder.append("class ").append(clazzName);

        if (null != superClazz && superClazz != Object.class) {
            resultBuilder.append(" extends ").append(superClazz);
        }

        Class[] interfaces = clazz.getInterfaces();
        for (int i = 0; i < interfaces.length; i++) {
            if (i == 0) {
                resultBuilder.append(" implements ");
            }
            else {
                resultBuilder.append(", ");
            }

            resultBuilder.append(interfaces[i].getName());
        }

        resultBuilder.append("{").append("\n\n");

        //build constructors
        buildConstructors(resultBuilder, clazz.getConstructors());

        //build fields
        buildFields(resultBuilder, clazz.getDeclaredFields());
        //build methods
        buildMethods(resultBuilder, clazz.getDeclaredMethods());

        return resultBuilder.append("}").toString();
    }

    private static void buildMethods(StringBuilder resultBuilder, Method[] declaredMethods) {
        for (int i = 0; i < declaredMethods.length; i++) {
            Method method = declaredMethods[i];
            resultBuilder.append("\t");
            String modifier = Modifier.toString(method.getModifiers());
            Class returnType = method.getReturnType();
            String methodName = method.getName();
            Class[] paramTypes = method.getParameterTypes();
            resultBuilder.append(modifier).append(" ")
                    .append(returnType.getTypeName()).append(" ")
                    .append(methodName).append("(");
            for (int j = 0; j <paramTypes.length ; j++) {
                if (j!=0){
                    resultBuilder.append(",");
                }
                resultBuilder.append(paramTypes[j].getName());
            }
            resultBuilder.append(methodName).append(");\n\n");

        }
    }

    private static void buildFields(StringBuilder resultBuilder, Field[] declaredFields) {
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            String modifiers = Modifier.toString(field.getModifiers());
            Class type = field.getType();
            String name = field.getName();
            resultBuilder.append("\t");
            if (StringUtils.isNotBlank(modifiers)) {
                resultBuilder.append(modifiers);
            }
            resultBuilder.append(" ").append(type).append(" ").append(name).append(";\n\n");
        }
    }

    private static void buildConstructors(StringBuilder resultBuilder, Constructor[] constructorClazz) {
        for (int i = 0; i < constructorClazz.length; i++) {


            Constructor conClazz = constructorClazz[i];
            String conModifier = Modifier.toString(conClazz.getModifiers());
            if (StringUtils.isNotBlank(conModifier)) {
                resultBuilder.append("\t").append(conModifier).append(" ");
            }

            resultBuilder.append(conClazz.getName()).append(" ( ");
            Class[] paramClass = conClazz.getParameterTypes();

            for (int j = 0; j < paramClass.length; j++) {
                if (j == 0) {
                    resultBuilder.append(paramClass[j].getName());
                }
                else {
                    resultBuilder.append(",").append(paramClass[j].getName());
                }
            }

            resultBuilder.append(conClazz.getName()).append(" );\n\n");
        }
    }

}
