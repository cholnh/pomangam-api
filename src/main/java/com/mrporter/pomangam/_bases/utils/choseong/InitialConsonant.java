package com.mrporter.pomangam._bases.utils.choseong;

public class InitialConsonant {
    final static String[] chs = {
            "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ",
            "ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ",
            "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ",
            "ㅋ", "ㅌ", "ㅍ", "ㅎ"
    };

    public static boolean isInitialConsonants(String fullStr) {
        fullStr = fullStr.replaceAll("\\p{Z}", "");
        for (int i=0; i<fullStr.length(); i++) {
            char chName = fullStr.charAt(i);
            boolean tf = false;
            for(String s : chs) {
                if(s.charAt(0) == chName) {
                    tf = true;
                    break;
                }
            }
            if(tf == false) {
                return false;
            }
        }
        return true;
    }

    public static String getInitial(String fullStr) {

        String resultStr="";
        for (int i=0; i<fullStr.length(); i++) {
            char chName = fullStr.charAt(i);
            if(chName >= 0xAC00) {
                int uniVal = chName - 0xAC00;
                int cho = ((uniVal - (uniVal % 28))/28)/21;
                resultStr += chs[cho];
            } else {
                resultStr += chName;
            }
        }
        return resultStr.replaceAll("\\p{Z}", "");
    }


    /*
        public static String getInitial(String fullStr){
            String resultStr="";
            for (int i=0; i<fullStr.length(); i++) {
                char comVal = (char) (fullStr.charAt(i)-0xAC00);
                if (comVal >= 0 && comVal <= 11172){
                    // 한글일경우
                    // 초성만 입력 했을 시엔 초성은 무시해서 List에 추가
                    char uniVal = (char)comVal;

                    // 유니코드 표에 맞추어 초성 중성 종성을 분리
                    char cho = (char) ((((uniVal - (uniVal % 28)) / 28) / 21) + 0x1100);
                    //char jung = (char) ((((uniVal - (uniVal % 28)) / 28) % 21) + 0x1161);
                    //char jong = (char) ((uniVal % 28) + 0x11a7);
                    if(cho!=4519){
                        //System.out.print(cho+" ");
                        resultStr =resultStr + (cho);
                    }

                    if(jung!=4519){
                        //System.out.print(jung+" ");
                    }
                    if(jong!=4519){
                        //System.out.print(jong+" ");
                    }

                } else {
                    // 한글이 아닐경우
                    comVal = (char) (comVal+0xAC00);
                    resultStr =resultStr + comVal;
                }
            }
            return resultStr.replaceAll("\\p{Z}", "");
        }
    */
    public static void main(String[] args) {
        System.out.println(isInitialConsonants("  ㄱsdf"));
        System.out.println(isInitialConsonants("  ㄱ간가나요!!"));
        System.out.println(isInitialConsonants("  ㄱ ㄴㅇㄹㄴㅇㄹ 2"));
        System.out.println(isInitialConsonants("  ㄱㄱ ㅁㄴㅇ ㅏ"));
        System.out.println(isInitialConsonants("  ㄱ"));
    }
}
