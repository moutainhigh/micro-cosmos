import com.alibaba.dubbo.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:thomas su
 * @Date: 2018/7/24 16:19
 * @Description:
 */
public class RegexTest {
    /**
     * 当规则编辑时，选择公式时，对应的字段code拼接前缀：FD_ ； 后缀：_FD
     * 如：sexScore+nameScore ==> FD_sexScore + FD_nameScore
     */
    public static final String FORMULA_PREFIX = "FD_";

    public static final String FORMULA_SUFFIX = "_FD";

    public static void main(String[] args){
        String judgeValue = "FD_SEX_SCORE_FD+FD_AGE_SCORE_FD";




//        String pattern = "^[FD_]([a-zA-Z_0-9]+)_FD$";
//        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(formula);
//        System.out.println(m.matches());
//        int groupCount = m.groupCount();
//        for(int i =0; i< groupCount ; i++){
//            System.out.println(m.group(i));
//        }


        String initFormula = judgeValue;
        //将"FD_SEX_SCORE_FD+FD_AGE_SCORE_FD" 替换为 "SEX_SCORE+AGE_SCORE"
        String formulaTmp = judgeValue;
        List<String> fieldCodes = new ArrayList<>();
        int leftIndex = formulaTmp.indexOf(FORMULA_PREFIX);
        int rightIndex = 0;
        String fieldCode = null;
        while (leftIndex != -1) {
            rightIndex = formulaTmp.indexOf(FORMULA_SUFFIX);
            fieldCode = formulaTmp.substring(leftIndex + FORMULA_PREFIX.length(), rightIndex);
            formulaTmp = formulaTmp.substring(rightIndex + FORMULA_SUFFIX.length());
            if (StringUtils.isNotEmpty(fieldCode)) {
                fieldCodes.add(fieldCode);
            }
            leftIndex = formulaTmp.indexOf(FORMULA_PREFIX);
        }

        String oldExper = null;
        String newExper = null;
        for(int i = 0; i< fieldCodes.size(); i++){
            oldExper = FORMULA_PREFIX + fieldCodes.get(i) +FORMULA_SUFFIX;
            newExper = "map.get(\""+ fieldCodes.get(i) + "\")";
            initFormula = initFormula.replace(oldExper, newExper);
        }

        System.out.println(initFormula);
    }
}
