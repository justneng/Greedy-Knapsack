import java.util.*;

/**
 * Created by wanchana on 8/11/2558.
 */
public class Knapsack {
    public List<GreedyKnapsack> knapsackAlgorithm(int[] price, int[] weight, int backWeight){

        List tmpList = new ArrayList();
        List products = new ArrayList();
        List<Double> tmpArr = new ArrayList<Double>();
        int sumWeight = 0;

        for (int i = 0; i < price.length; i++){
            double pricePerWeight;
            Map hashMap = new HashMap();
            pricePerWeight = Math.round(((float)price[i])/((float)weight[i])*100.0)/100.0;

            tmpArr.add(i, pricePerWeight);
            hashMap.put("choice", i + 1);
            hashMap.put("weight", weight[i]);
            hashMap.put("price", price[i]);
            hashMap.put("pricePerWeight", pricePerWeight);
            tmpList.add(hashMap);
        }

        Collections.sort(tmpArr, Collections.reverseOrder());

        for (int idx = 0; idx < tmpArr.size(); idx ++){
            for(int index = 0; index < tmpList.size(); index ++){
                HashMap product = (HashMap) tmpList.get(index);
                if(sumWeight <= backWeight){
                    if((((Double) product.get("pricePerWeight") - tmpArr.get(idx)) == 0) && ((sumWeight + (int)product.get("weight")) <= backWeight)){
                        List checkDuplicate = new ArrayList();
                        checkDuplicate.add(product);

                        if((idx == (tmpList.size() - 1) && (checkDuplicate.size() > 1))){
                            List<Integer> selectWeight = new ArrayList();
                            HashMap selectMap = null;
                            for(int j = 0; j < checkDuplicate.size(); j++){
                                selectMap = (HashMap) checkDuplicate.get(j);
                                selectWeight.add((Integer) selectMap.get("weight"));
                            }

                            Collections.sort(selectWeight);

                            for(int k = 0;k < selectWeight.size(); k++){
                                if((((Integer)selectMap.get("weight") - selectWeight.get(k)) == 0) && ((sumWeight + (Integer)selectMap.get("weight")) <= backWeight)){
                                    products.add(product);
                                    sumWeight = sumWeight + (int)product.get("weight");
                                    tmpList.remove(product);
                                }
                            }
                        }
                        else{
                            products.add(product);
                            sumWeight = sumWeight + (int)product.get("weight");
                            tmpList.remove(product);
                        }
                    }
                }
                else{
                    break;
                }
            }
        }

        List<GreedyKnapsack> greedyKnapsackList = new ArrayList<>();
        for(int m = 0; m < products.size(); m ++){
            HashMap p = (HashMap) products.get(m);
            GreedyKnapsack greedyKnapsack = new GreedyKnapsack();
            greedyKnapsack.setChoice((Integer) p.get("choice"));
            greedyKnapsack.setPrice((Integer) p.get("price"));
            greedyKnapsack.setWeight((Integer) p.get("weight"));
            greedyKnapsack.setPricePerWeight((Double) p.get("pricePerWeight"));
            greedyKnapsackList.add(greedyKnapsack);
        }
        return greedyKnapsackList;
    }
}
