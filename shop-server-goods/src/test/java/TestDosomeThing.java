import java.util.ArrayList;
import java.util.List;

public class TestDosomeThing {


    public static void main(String[] args) {
        //定义颜色分类
        List<String> colors = new ArrayList<String>();
        colors.add("白色");
        colors.add("银色");
        colors.add("黑色");
        colors.add("金色");

//定义内存大小
        List<String> capacitys = new ArrayList<String>();

        capacitys.add("32G");
        capacitys.add("64G");
        capacitys.add("128G");
        capacitys.add("256G");

//定义一个集合
        List<List<String>> list = new ArrayList<List<String>>();
        list.add(colors);
        list.add(capacitys);
        int i = 0;
        getSKU(list,i);


    }


    public static void getSKU(List<List<String>> list,int index) {

        for (List<String> strings : list) {
            StringBuilder skuStr = new StringBuilder();
            skuStr.append(list.get(index).get(index));
            if(++index < list.size()){
                getSKU( list, index);
            }
        }

    }

}
