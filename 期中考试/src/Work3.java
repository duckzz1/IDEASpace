
public class Work3 {

    public static void main(String[] args) {
        String str = "please sit spot.sit spot,sit.spot here now here.";
        System.out.println(str);
        System.out.println(addComma(str));
    }

    public static String addComma(String str){
        int s = 0;
        char[] ch = str.toCharArray();
        StringBuffer sb = new StringBuffer(str);
        int[] isWhat = detect(str);
        int wordNumber = wordNumer(isWhat);
        String[] word = subForWord(str, isWhat, wordNumber);
        int[] isSame = isSame(word, wordNumber);
        int[][] match = getMatch(str, word, isWhat, wordNumber, isSame);

        for (int i = 0; i < wordNumber; i++) {
            int wordIndex = 0;
            for(int m = 0; m < i; m++){
                wordIndex = wordIndex + word[m].length()+1;
            }
            int head = wordIndex-1;
            int back = wordIndex+word[i].length();
            if (head >= 0){
                if(ch[head] == ',') {
                    for (int j = 0; j < isSame[i]; j++) {
                        int myhead = match[i][j] - 1;
                        if (myhead != -1 && ch[myhead] == ' ' && ch[myhead] != '.' && ch[myhead] != ',') {
                            sb.setCharAt(myhead, ',');
                            s++;
                        }
                    }
                }
            }
            if (back < str.length()){
                if(ch[back] == ',') {
                    for (int j = 0; j < isSame[i]; j++) {
                        int myback = match[i][j]+word[i].length();
                        if (ch[myback]!='.' && ch[myback]!=',' && ch[myback] == ' '){
                            sb.setCharAt(myback, ',');
                            s++;
                        }
                    }
                }
            }
        }
        if (s > 0){
            return addComma(sb.toString());
        }else{
            return sb.toString();
        }
    }

    public static int[] isSame(String[] word, int wordNumber) {
        int[] isSame = new int[wordNumber];
        for (int i = 0; i < wordNumber; i++) {
            int count = 0;
            for (int j = 0; j < wordNumber; j++){
                if (word[i].equals(word[j])){
                    count++;
                }

            }
            isSame[i] = count;
        }

        return isSame;
    }

    /**
     *
     * @return 相同单词的索引
     */
    public static int[][] getMatch(String str, String[] word, int[] isWhat, int wordNumber, int[] isSame) {
        int[][] match = new int[wordNumber][5];
        for (int i = 0; i < wordNumber; i++) {
            int temp = 0;
            for (int j= 0; j <= isSame[i]; j++) {
                temp = str.indexOf(word[i], temp);
                match[i][j] = temp;
                temp++;
            }
        }
        return match;
    }

    /**
     * 0:字母，1：逗号， 2：句号， 3：空格
     * @param str 检测的字符串
     * @return 表示字符属性的数组
     */
    public static int[] detect(String str) {
        char[] ch = str.toCharArray();
        int[] s = new int[str.length()];
        for (int i = 0; i < ch.length; i++) {
            if (ch[i]>='a' && ch[i]<='z') {
                s[i] = 0;
            }
            if (ch[i]==',') {
                s[i] = 1;
            }
            if (ch[i]=='.') {
                s[i] = 2;
            }
            if (ch[i] == ' '){
                s[i] = 3;
            }
        }
        return s;
    }

    public static int wordNumer(int[] isWhat){
        int number = 0;
        for (int i = 0; i < isWhat.length; i++) {
            if(isWhat[i]!=0) {
                number++;
            }
        }
        return number;
    }

    public static String[] subForWord(String str, int[] isWhat, int wordNumber) {
        char[] ch = str.toCharArray();
        String[] word = new String[wordNumber];
        int count = 0;
        for (int i = 0; i < wordNumber; i++) {
            StringBuffer sb = new StringBuffer();
            boolean flag = true;
            while (flag){
                if (isWhat[count]!=0){
                    flag = false;
                    count++;
                    break;
                }else{
                    sb.append(ch[count]);
                    count++;
                }
            }
            word[i] = sb.toString();
        }
        return word;
    }
}
