package everyDay;

public class DefangIpAddr {

    public String defangIPaddr(String address) {
        StringBuilder builder = new StringBuilder(address);
        for (int i = 0; i < builder.length(); i++) {
            if(address.charAt(i) == '.'){
                builder.append('[');
                builder.append('.');
                builder.append(']');
            }else {
                builder.append(address.charAt(i));
            }
        }
        return builder.toString();
    }

}
