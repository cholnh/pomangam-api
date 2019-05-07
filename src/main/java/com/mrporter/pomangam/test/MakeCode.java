package com.mrporter.pomangam.test;

public class MakeCode {
    public static void main(String[] args) {
        makeCode();
    }

    public static void makeCode() {
        String jpaName = "imageForStores";
        String[] fields = "store_idx, imgpath, type"
                .replaceAll("\\p{Z}", "")
                .split(",");

        /* make start */
        System.out.println("/* start code */");
        System.out.println(
                "    <h2>" + jpaName + "</h2>\n" +
                "\n" +
                "    <form id=\"form\">");
        for(String field : fields) {
            System.out.println(
                "        <input type=\"text\" id=\"" + field + "\" placeholder=\"" + field + "\"><br>");
        }
        System.out.println(
                "        <button type=\"button\" id=\"submit\">생성</button>\n" +
                "        <button type=\"reset\" id=\"reset\">초기화</button>\n" +
                "    </form>\n" +
                "\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" +
                "    <script src=\"https://unpkg.com/bootstrap-table@1.14.2/dist/bootstrap-table.min.js\"></script>\n" +
                "    <script src=\"../../js/custom.js\"></script>\n" +
                "    <script th:inline=\"javascript\">\n" +
                "        /*<![CDATA[*/\n" +
                "        var token = [[${session.token}]];\n" +
                "        /*]]>*/\n" +
                "    </script>");
        String data = "";
        for(int i=0; i<fields.length; i++) {
            data += "\t\t\t\t\"" + fields[i] + "\" : $('#" + fields[i] + "').val()";
            if(i!=fields.length-1) {
                data += ",";
            }
            data += "\n";
        }

        System.out.println(
                "    <script>\n" +
                "        $('#submit').click(function () {\n" +
                "            if(token) {\n" +
                "                exec();\n" +
                "            } else {\n" +
                "                alert('token is null');\n" +
                "            }\n" +
                "        });\n" +
                "        function exec() {\n" +
                "            const data = {\n" + data +
                "            };\n" +
                "            postJpa('" + jpaName + "', token, data, function(e) {\n" +
                "                alert('생성완료');\n" +
                "                $('#reset').click();\n" +
                "            });\n" +
                "        }\n" +
                "    </script>");
        System.out.println("/* end code */");
        /* make end */
    }
}
