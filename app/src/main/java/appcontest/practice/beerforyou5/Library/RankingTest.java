package appcontest.practice.beerforyou5.Library;

/**
 * Created by jiuser on 2015-11-21.
 */

public class RankingTest {
    private static int numOfRecommendation = 50;
    public static int[] rule1_positive = {0,0,0,0,0,0,1,1,1,1,1,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,5,6,6,6,6,6,7,7,7,7,7,9,9,9,9,9,9,9,9,10,10,10,10,11,11,11,11,11,11,11,11,12,13,13,13,13,13,13,13,14,14,14,14,14,14,14,14,15,15,15,15,15,16,16,16,16,16,16,16,17,17,17,18,18,18,18,18,19,19,19,19,19,19,19,19,20,20,21,21,21,21,21,22,22,22,22,23,23,23,23,23,24,25,25,25,26,26,26,26,26,26,26,26,27,29,29,29,29,29,29,30,30,30,30,30,30,31,31,31,31,31,31,31,31,32,32,32,32,33,33,33,33,34,34,34,34,34,34,35,35,35,36,36,37,37,38,38,38,38,38,38,38,39,39,40,40,40,40,40,40,41,42,42,42,43,43,44,44,45,45,45,46,46,47,47,47,47,48,48,48,48,48,48,49,49,49,49
    };
    public static int[] rule2_positive = {1,11,19,21,33,34,5,7,11,23,26,9,10,18,26,10,11,16,19,26,33,38,1,11,16,17,19,21,23,26,27,1,11,16,26,30,31,1,14,23,26,30,10,11,16,18,19,21,23,26,3,11,19,38,3,10,14,21,26,38,42,48,11,17,18,19,21,23,26,33,7,11,16,23,26,33,40,11,5,10,11,16,34,3,11,14,23,26,33,40,19,21,33,9,10,11,21,23,0,3,5,9,10,17,26,33,11,26,4,9,11,17,18,10,11,23,26,7,14,26,33,34,10,11,21,23,1,9,11,20,23,33,38,49,4,11,16,30,31,33,36,5,6,7,11,16,26,3,6,16,23,29,33,49,33,11,14,16,33,16,23,26,45,0,3,11,16,23,33,1,19,33,11,29,11,34,3,10,11,21,26,33,45,21,33,3,11,14,16,21,26,10,11,18,45,11,33,21,48,21,33,42,23,33,3,11,33,11,10,11,14,21,26,33,9,11,14,16
    };
    public static double[] lift_positive = {1.18693,1.16857,1.2794,1.16043,1.17459,1.25583,1.17155,1.25828,1.14642,1.16112,1.20179,1.21664,1.1886,1.2546,1.19928,1.15703,1.15095,1.17052,1.21143,1.13759,1.13759,1.19181,1.168,1.1756,1.20307,1.21449,1.19145,1.23037,1.1294,1.13517,1.34779,1.17155,1.1908,1.21329,1.20484,1.27877,1.26879,1.25828,1.21198,1.23145,1.15231,1.22562,1.13963,1.14851,1.16275,1.26078,1.18812,1.21385,1.15098,1.1868,1.15703,1.23086,1.27549,1.20847,1.15095,1.23086,1.24174,1.16394,1.15467,1.25175,1.23116,1.22692,1.17702,1.18991,1.21136,1.1991,1.19376,1.20124,1.17369,1.15027,1.21198,1.24174,1.16117,1.18331,1.15527,1.15772,1.21803,1.2,1.20105,1.19548,1.15257,1.19016,1.24462,1.17052,1.13191,1.16117,1.14888,1.14272,1.21353,1.30901,1.20293,1.21071,1.1737,1.26078,1.17982,1.13842,1.27107,1.16108,1.2794,1.21143,1.17414,1.18812,1.27549,1.20293,1.15521,1.17024,1.13559,1.23219,1.23037,1.21385,1.16394,1.21071,1.27107,1.17386,1.17831,1.18615,1.19492,1.23145,1.18331,1.17372,1.14039,1.20767,1.20096,1.24844,1.21565,1.21587,1.20179,1.1868,1.15467,1.23219,1.17372,1.18,1.18845,1.25402,1.34779,1.19234,1.24206,1.26864,1.28702,1.18231,1.30211,1.20848,1.27877,1.22562,1.13424,1.21306,1.18123,1.16689,1.26879,1.19077,1.15586,1.28702,1.17225,1.33938,1.2,1.25826,1.21137,1.20317,1.21665,1.21353,1.14039,1.18,1.19792,1.25583,1.1515,1.15028,1.17973,1.20767,1.18393,1.20745,1.19922,1.18638,1.20054,1.30211,1.16088,1.23403,1.19181,1.20847,1.25175,1.15274,1.18845,1.15343,1.18213,1.20431,1.20524,1.15516,1.167,1.21803,1.30901,1.16202,1.13978,1.16564,1.23116,1.20928,1.2162,1.1757,1.1686,1.21608,1.18583,1.14743,1.19792,1.2162,1.24253,1.21395,1.21441,1.15292,1.22452,1.2,1.17845,1.22692,1.16425,1.15022,1.15843,1.16093,1.20585,1.15106,1.21003,1.22593
    };

    public static int[] rule1_negative = {0,0,1,1,1,2,2,3,3,3,4,5,6,7,7,7,8,8,9,9,9,9,10,10,10,10,11,12,13,13,13,13,13,13,14,14,15,15,16,16,16,16,17,17,17,17,18,18,18,18,19,19,19,19,20,20,21,21,21,21,22,22,22,23,23,23,23,24,24,24,24,25,25,25,26,26,26,26,26,27,27,28,28,28,28,29,30,30,30,31,31,31,31,32,33,33,33,34,34,34,35,35,35,36,36,37,38,38,38,38,38,39,40,40,41,41,41,41,42,42,42,43,43,43,43,44,44,45,45,46,47,47,47,48,49,49,49
    };
    public static int[] rule2_negative = {28,0,17,32,1,44,2,10,14,3,4,5,6,14,32,7,3,8,3,28,29,9,3,19,32,10,11,12,1,14,16,32,44,13,3,14,32,15,3,28,32,16,1,3,36,17,1,14,19,18,3,10,14,19,14,20,14,19,28,21,14,28,22,3,14,29,23,3,14,29,24,14,30,25,2,3,17,28,26,14,27,9,22,44,28,29,14,28,30,14,32,43,31,32,32,43,33,14,28,34,3,28,35,17,36,37,14,28,32,44,38,39,14,40,14,32,36,41,14,44,42,28,32,44,43,43,44,14,45,46,17,28,47,48,3,14,49
    };
    public static double[] lift_negative = {-1.23134,-3.6,-1.2106,-1.18969,-3.6,-1.19823,-3.6,-1.27134,-1.18237,-3.6,-3.6,-3.6,-3.6,-1.16892,-1.20091,-3.6,-1.22302,-3.6,-1.18655,-1.28985,-1.25792,-3.6,-1.27134,-1.25549,-1.18573,-3.6,-3.6,-3.6,-1.27386,-1.21829,-1.24028,-1.19126,-1.28232,-3.6,-1.18237,-3.6,-1.19929,-3.6,-1.21353,-1.20299,-1.18568,-3.6,-1.2106,-1.19336,-1.26928,-3.6,-1.27312,-1.15671,-1.28761,-3.6,-1.18897,-1.25549,-1.21422,-3.6,-1.18412,-3.6,-1.15618,-1.23284,-1.21783,-3.6,-1.16624,-1.29105,-3.6,-1.24505,-1.22142,-1.21117,-3.6,-1.1854,-1.19598,-1.19103,-3.6,-1.17565,-1.25717,-3.6,-1.30548,-1.17927,-1.21127,-1.22167,-3.6,-1.16923,-3.6,-1.28985,-1.29105,-1.19654,-3.6,-3.6,-1.18685,-1.23021,-3.6,-1.1803,-1.20519,-1.24619,-3.6,-3.6,-1.1972,-1.25349,-3.6,-1.19748,-1.20391,-3.6,-1.19375,-1.21074,-3.6,-1.26928,-3.6,-3.6,-1.17658,-1.2164,-1.19144,-1.18817,-3.6,-3.6,-1.14883,-3.6,-1.18863,-1.18529,-1.24334,-3.6,-1.20221,-1.18868,-3.6,-1.20545,-1.21912,-1.2591,-3.6,-1.2591,-3.6,-1.19054,-3.6,-3.6,-1.28619,-1.20889,-3.6,-3.6,-1.19777,-1.16316,-3.6
    };

    public static double[] calPredictedScorePositive(int[] positions){
        int i, j;
        int found_flag = 0;
        double[] result_score = new double[numOfRecommendation];

        for(i=0; i < positions.length; i++){

            for(j=0; j<rule1_positive.length; j++){

                if( rule1_positive[j] == positions[i] ){
                    result_score[rule2_positive[j]] = result_score[rule2_positive[j]] + lift_positive[rule2_positive[j]];
                    found_flag=1;
                }
                // if we found the rule sequence is over...so we quit for j statement
                if( j == rule1_positive.length-1)break;
                if( found_flag==1 && ( rule1_positive[j] != rule1_positive[j+1])){
                    break;
                }
                found_flag=0;
            }
        }
        return result_score;
    }
    public static double[] calPredictedScoreNegative(int[] positions){
        int i, j;
        int found_flag = 0;
        double[] result_score = new double[numOfRecommendation];

        for(i=0; i < positions.length; i++){

            for(j=0; j<rule1_negative.length; j++){

                if( rule1_negative[j] == positions[i] ){
                    result_score[rule2_negative[j]] = result_score[rule2_negative[j]] + lift_negative[rule2_negative[j]];
                    found_flag=1;
                }
                // if we found the rule sequence is over...so we quit for j statement
                if( j == rule1_negative.length-1)break;
                if(found_flag==1 && ( rule1_negative[j] != rule1_negative[j+1])){
                    break;
                }
                found_flag=0;
            }
        }
        return result_score;
    }

    public static int[] findRankingSequence(double[] result_score){
        int[] ranking_sequence = new int[numOfRecommendation];
        double[] result_score_sorted = new double[numOfRecommendation];
        double temp1;
        int i, j;
        for(i=0; i < result_score_sorted.length; i++){
            result_score_sorted[i] = result_score[i];
        }

        ///// sort result_score by using quick sort
        Quicksort sorter = new Quicksort();
        sorter.sort(result_score_sorted);
        if (!sorter.validate(result_score_sorted)) {
            System.out.println(" Quicksort : FAIL ");
        }

        ///// pick temp1 from beginning of sorted array (highest value)
        ///// find matched position which is the highest value's position
        for(i=0 ; i<result_score_sorted.length ;i++){
            temp1 = result_score_sorted[result_score_sorted.length-1-i];
            for(j=0; j< result_score.length; j++ ){
                if( temp1 == result_score[j] ){
                    ranking_sequence[i] = j;
                }
            }
        }
        return ranking_sequence;
    }
}