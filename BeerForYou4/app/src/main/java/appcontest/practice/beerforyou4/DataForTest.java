package appcontest.practice.beerforyou4;

import android.util.Log;

public class DataForTest{

    static String[] name_eng = {
            "SAPPORO",
            "Hoegaarden",
            "Asahi",
            "Guinness",
            "San Miguel",
            "Heineken",
            "Santory",
            "Efes",
            "Budwiser",
            "Tsingtao",
            "Corona",
            "Toohey's old",
            "Baltika",
            "Stella Artrois",
            "Edelwises Snowfresh",
            "Duvel",
            "Dos Equis",
            "Tiger Gold Medal",
            "London Pride",
            "Carlsberg",
            "Kronenbourg"
    };
    static String[] name_kor = {
            "삿포로",
            "호가든",
            "아사히",
            "기네스",
            "산미구엘",
            "하이네켄",
            "산토리",
            "에페스",
            "버드와이저",
            "칭타오",
            "코로나",
            "투이스올드",
            "발티카",
            "스텔라 아트루아",
            "에델바이스 스노우후레시",
            "두블 (=악마)",
            "도스 에퀴스",
            "타이거",
            "런던프라이드",
            "칼즈버그",
            "크로넨버그"
    };
    static String[] nation = {
            "일본",
            "벨기에",
            "일본",
            "아일랜드",
            "필리핀",
            "네덜란드",
            "일본",
            "터키",
            "미국",
            "중국",
            "멕시코",
            "호주",
            "러시아",
            "벨기에",
            "오스트리아",
            "벨기에",
            "멕시코",
            "싱가포르",
            "영국",
            "덴마크",
            "프랑스"
    };
    static String[] alcohol_degree = {
            "5",
            "4.9",
            "5",
            "4.2",
            "5",
            "5",
            "5.5",
            "5",
            "5",
            "4.7",
            "Null",
            "4.4",
            "5.4",
            "5",
            "5",
            "8.5",
            "4.7",
            "5",
            "4.7",
            "5",
            "5"
    };
    static String[] taste = {
            "가벼운 맛, 끝 맛이 깔끔함.",
            "보리 냄새를 맡을 수 있다는 라거 맥주. 월드 비어컵 4회 연속",
            "탄산은 그리 강하지 않아서 쓴 맛은 적음, 부드러운 목넘김",
            "흑맥주, 부드러운 거품, 커피의 씁쓸함을 느낄 수 있는 아일랜드 대표맥주",
            "톡 쏘는 홉 뒤의 부드러운 맛, 맛이 엷어 시원하게 마시는게 좋아요",
            "탄산기 높은 라거 맥주, 끝 맛이 살짝 씁쓸하다는 평도..",
            "탄산기가 높지만 부드러운 목넘김과 구수한 끝 맛이 특징",
            "톡 쏘는 맛과 신맛의 조화, 향도 뭔가 시큼!",
            "탄산이 강하고 목 넘김이 강함. 쌀 함유량이 많아서 술 특유의 맛을 줄였다고 하네요.",
            "맛은 다른 맥주보다 조금 연하지만",
            "레몬, 라임을 넣었을 때 잘 어울리는 맥주. 코로나리타 등의 칵테일로도 많이 먹어요.",
            "흑맥주, 굉장히 부드럽고 목넘김이 좋음",
            "풍부한 거품과 깔끔한 맛, 꿀맛이 난다네요.",
            "라이트하고 깔끔, 과일향이 느껴지는 맥주",
            "첫맛은 신선, 끝맛은 달콤씁쓸, 목 넘김과 함께 느껴지는 은은한 끝향기",
            "향이 풍부하고 거품이 많아 목넘김은 좋지만 강한 도수로 취하기 좋음, 떫은 사과 맛이 느껴지고 끝맛은 드라이, 10도 정도에서 드세요",
            "탄산이 강함, 거친 남자의 느낌",
            "전체적으로 밋밋한 맛이지만 살짝 감도는 홉의 씁쓸함, 차게 드세요!",
            "거품이 적어서 묵직한 맛, 마멀레이드와 오렌지향이 도는 끝맛",
            "감귤향과 어우러진 가벼운 맛, 남녀 노소 누구나 즐길 수 있는 맥주의 기본",
            "부드럽고 청량한 맛, 가볍게 마실 수 있음"
    };

    static Integer[] images = {
            R.drawable.sapporo,
            R.drawable.hoegaarden,
            R.drawable.asahi,
            R.drawable.guinness,
            R.drawable.sanmiguel,
            R.drawable.heineken,
            R.drawable.santory,
            R.drawable.efes,
            R.drawable.budwiser,
            R.drawable.tsingtao,
            R.drawable.corona,
            R.drawable.tooheysold,
            R.drawable.baltika,
            R.drawable.stellaartrois,
            R.drawable.edelwises,
            R.drawable.duvel,
            R.drawable.dosequis,
            R.drawable.tigergoldmedal,
            R.drawable.londonpride,
            R.drawable.carlsberg,
            R.drawable.kronenbourg
    };

    // Input : name or text of beer(String)
    // output : position of name or text of beer to indicate which one it is ( int )
    // position can range from 0 to 100
    public static int findItemPositionByText(String text){
        int itemPosition=0;
        int numBeer = name_eng.length;
        int[] editDistance = new int[numBeer];
        int minEditDistance;
        text = text.toLowerCase();
        editDistance[0] = minDistance(text, name_eng[0].toLowerCase() );
        minEditDistance = editDistance[0];
        for(int i =1; i<numBeer; i++){
            editDistance[i] = minDistance(text, name_eng[i].toLowerCase() );
            if( editDistance[i] < minEditDistance){
                minEditDistance = editDistance[i];
                itemPosition = i;
            }
        }
        Log.i("Data For Test", "Input Text of Voice Recognition : " + text );
        Log.i("Data For Test", "name_eng(Lower Case) : "+ name_eng[itemPosition].toLowerCase() );
        Log.i("Data For Test", "minEditDistance : " + minEditDistance );
        Log.i("Data For Test", "itemPosition : "+ itemPosition );
        return itemPosition;
    }

    // Calculate the edit distance between two words
    public static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        // len1+1, len2+1, because finally return dp[len1][len2]
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        //iterate though, and check last char
        for (int i = 0; i < len1; i++) {
            char c1 = word1.charAt(i);
            for (int j = 0; j < len2; j++) {
                char c2 = word2.charAt(j);

                //if last two chars equal
                if (c1 == c2) {
                    //update dp value for +1 length
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    int replace = dp[i][j] + 1;
                    int insert = dp[i][j + 1] + 1;
                    int delete = dp[i + 1][j] + 1;

                    int min = replace > insert ? insert : replace;
                    min = delete > min ? min : delete;
                    dp[i + 1][j + 1] = min;
                }
            }
        }

        return dp[len1][len2];
    }

}
