package appcontest.practice.beerforyou5.Library;

import android.util.Log;

import appcontest.practice.beerforyou5.R;

public class DataForTest{

    // 세계 맥주 순위 탑 5
    // 1. 파울라너 =
    // 2. 호가든 = 1
    // 3. 기네스 = 3
    // 4. 아사히 = 2
    // 5. 하이네켄 = 5
    public static String[] top5_name_eng = {
            "Hoegaarden",
            "Guinness",
            "Asahi",
            "Heineken",
            "Santory"
    };

    public static String[] url_content = {
            "Hoegaarden",
            "http://www.hoegaarden.co.kr/brandStory/brandIntro.asp",
            "Asahi",
            "https://www.heineken.com/kr/AgeGateway.aspx",
            "Santory"
    };

    public static String[] name_eng = {
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
            "Kronenbourg",
            "Kozel Dark",
            "Pilsner Urquell",
            "Miller",
            "Beck's",
            "L Wizen",
            "Krombacher",
            "Erdinger Weissbier",
            "Bud Ice",
            "Tuborg",
            "Dry Finish",
            "Max",
            "Sol",
            "Singha",
            "Kloud",
            "Maisel Weisse",
            "Queen's Ale",
            "New Castle",
            "Das Ambre",
            "Dos Equis",
            "Cannabia",
            "Kirin Ichiban",
            "Victoria Bitter",
            "Stout",
            "Krone Neptun",
            "Honey Dew",
            "Birra Moretti",
            "Brrr",
            "Great White",
            "Carling"
    };
    public static String[] name_kor = {
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
            "크로넨버그",
            "코젤 다크",
            "필스너우르켈",
            "밀러",
            "벡스",
            "엘바이젠",
            "크롬바커",
            "에딩거 바이스비어",
            "버드아이스",
            "투보그",
            "드라이피니시",
            "맥스",
            "솔",
            "싱하",
            "클라우드",
            "마이셀 바이스",
            "퀸즈에일",
            "뉴캐슬",
            "다스 암브레",
            "도스 에끼스",
            "카나비아",
            "기린 이치방",
            "빅토리아 빅터",
            "스타우트",
            "크로네 넵튠",
            "허니듀",
            "비라 모레띠",
            "브르르르",
            "그레이트 화이트",
            "칼링"
    };
    public static String[] nation = {
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
            "프랑스",
            "체코",
            "체코",
            "미국",
            "독일",
            "독일",
            "독일",
            "독일",
            "미국",
            "덴마크",
            "한국",
            "한국",
            "멕시코",
            "태국",
            "한국",
            "독일",
            "한국",
            "영국",
            "벨기에",
            "멕시코",
            "독일",
            "일본",
            "호주",
            "한국",
            "독일",
            "영국",
            "이탈리아",
            "미국",
            "미국",
            "영국"
    };
    public static String[] alcohol_degree = {
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
            "4.6",
            "4.4",
            "5.4",
            "5",
            "5",
            "8.5",
            "4.7",
            "5",
            "4.7",
            "5",
            "5",
            "3.8",
            "4.4",
            "4.8",
            "5",
            "4.9",
            "4.8",
            "5.3",
            "5.5",
            "4.6",
            "5",
            "5",
            "4.5",
            "5",
            "5",
            "5",
            "5.4",
            "4.7",
            "6.5",
            "4.5",
            "5",
            "5.6",
            "4.6",
            "5",
            "4.9",
            "5",
            "4.5",
            "7.2",
            "4.6",
            "4"
    };
    public static String[] taste = {
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
            "부드럽고 청량한 맛, 가볍게 마실 수 있음",
            "흑맥주의 진함이 부담스럽다면 이 맥주를, 진하진 않지만 가벼운 깊은 맛을 충분히 느낄 수 있다.",
            "연한 단맛으로 시작해 느껴지는 쌉싸름한 맛, 살짝의 샴페인 향이 풍미를 더한다.",
            "아로마 향과 홉의 들큰함이 조화되며 시원하고 깨끗한 느낌, 목넘김이 쉬움",
            "순한 라거의 느낌에 홉의 진한 느낌이 얹어져 있음",
            "첫 향이 강하지만 쌉싸름한 맛에 녹아가며 조화를 이룸",
            "향긋한 맛으로 시작, 가벼운 필스너의 향으로 필스너 입문에 좋은 맥주",
            "꽃향기의 절정을 이루는 맥주, 달콤한 맛 밑에 깔려있는 구수한 맛",
            "끝맛의 구수함이 일품, 하지만 라거답게 무겁지는 않은 맛",
            "군더더기가 없는 깔끔한 맛, 어느 상황에서나 가볍게 즐길 수 있음",
            "국내 생산 맥주중 홉의 맛을 잘 살린 편에 속하는 맥주, 들큰한 느낌은 덤",
            "국내 생산 맥주중 가장 진한 홉의 맛을 가지고 있지만 부드럽다는 것이 매력적인 맥주",
            "맑고 투명한 색과는 다르게 들큰한 맛이 느껴지는 맥주",
            "부드럽고 구수한 맥주 맛을 즐긴다면 추천하는 맥주",
            "물 없이 100%몰트로만 만들어 풍부하고 크리미한 맛",
            "꽃향기가 느껴지며 목 넘김이 좋은 농밀한 맛",
            "꽃 향이 모금마다 느껴지는 에일의 정석",
            "달달하지만 과하지 않고 풍미가 진한 맛",
            "쌉싸름함보다는 부드러움이 강조되는 IPA 맥주",
            "새콤한 느낌이 강한 맛",
            "세계 최초로 대마를 사용한 맥주,감칠맛과 쌉싸름함을 동시에 느낄 수 있다",
            "일본 맥주 판매 1위, 진한 홉의 첫 느낌이 살아 있다",
            "쌉싸름하게 시작하여 구수한 여운을 남기는 맛",
            "흑맥주의 쌉싸름함보단 부드러운 들큰함이 강조된다",
            "필스너 치고는 약한 쌉싸름함으로 필스너 입문용으로 좋다",
            "달콤한 벌꿀맛이 입안에 맴도는 맥주",
            "처음에는 강하게 느껴지는 홉의 향이 서서히 여운을 남기며 사라지는 맛",
            "에일 특유의 향이 점점 강하게 느껴지는 맛",
            "라거의 깔끔함을 담고 있는 밀맥주",
            "진한 구수함이 인상적인 영국의 라거"
    };

    public static Integer[] images = {
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
            R.drawable.kronenbourg,
            R.drawable.kronenbourg,//// kozel darkTODO: 2015-11-25
            R.drawable.pilsnerurquell,
            R.drawable.miller,
            R.drawable.becks,
            R.drawable.kronenbourg,// // L weizenTODO: 2015-11-25
            R.drawable.krombacher,
            R.drawable.erdingerweissbier,
            R.drawable.bud_ice,
            R.drawable.tuborg,
            R.drawable.dryfinish,
            R.drawable.max,
            R.drawable.sol,
            R.drawable.singha,
            R.drawable.kloud,
            R.drawable.maisselswisse,
            R.drawable.Queensale,
            R.drawable.newcastle,
            R.drawable.Dasambre,
            R.drawable.dosequis,
            R.drawable.Cannabia,
            R.drawable.kirin,
            R.drawable.Victoriabitter,
            R.drawable.stout,
            R.drawable.kroneneptune,
            R.drawable.honeydew,
            R.drawable.bierramoretti,
            R.drawable.brrr,
            R.drawable.greatwhite,
            R.drawable.carling
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
