package tool.common_useage_apis;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class NetWorkUtils {

    private NetWorkUtils() {}

    /*判断网络是否连接*/
    public static boolean netConnected(final Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    /*获取本地ip*/
    public static String getLocalNetIp(final Context context) {

        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        if (dhcpInfo == null) {

            return null;
        }
        int ipAddress = dhcpInfo.ipAddress;
        return intToIp(ipAddress);
    }

    public static String getLocalIpV4Address() {
        try {
            String ipv4;
            ArrayList<NetworkInterface> networkInterfaceArrayList = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface ni : networkInterfaceArrayList) {

                ArrayList<InetAddress> addressArrayList = Collections.list(ni.getInetAddresses());
                for (InetAddress address : addressArrayList) {

                    if (!address.isLoopbackAddress() && !address.isLinkLocalAddress()) {

                        ipv4 = address.getHostAddress();
                        return ipv4;
                    }
                }
            }
        } catch (SocketException ex) {

            ex.printStackTrace();
        }

        return "0.0.0.0";
    }

    public static String getIpAddressForInterfaces(){

        String interfaceName = "eth0";

        try {
            Enumeration<NetworkInterface> enNetworkInterface = NetworkInterface.getNetworkInterfaces(); //获取本机所有的网络接口
            while (enNetworkInterface.hasMoreElements()) {  //判断 Enumeration 对象中是否还有数据
                NetworkInterface networkInterface = enNetworkInterface.nextElement();   //获取 Enumeration 对象中的下一个数据
                if (!networkInterface.isUp()) { // 判断网口是否在使用
                    continue;
                }
                if (!interfaceName.equals(networkInterface.getDisplayName())) { // 网口名称是否和需要的相同
                    continue;
                }
                Enumeration<InetAddress> enInetAddress = networkInterface.getInetAddresses();   //getInetAddresses 方法返回绑定到该网卡的所有的 IP 地址。
                while (enInetAddress.hasMoreElements()) {
                    InetAddress inetAddress = enInetAddress.nextElement();
                    if (inetAddress instanceof Inet4Address) {  //判断是否未ipv4
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /*获取子网掩码*/
    public static String getIpAddressMaskForInterfaces() {

        String interfaceName = "eth0";

        try {
            //获取本机所有的网络接口
            Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            //判断 Enumeration 对象中是否还有数据
            while (networkInterfaceEnumeration.hasMoreElements()) {

                //获取 Enumeration 对象中的下一个数据
                NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();
                if (!networkInterface.isUp() && !interfaceName.equals(networkInterface.getDisplayName())) {

                    //判断网口是否在使用，判断是否时我们获取的网口
                    continue;
                }

                for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                    if (interfaceAddress.getAddress() instanceof Inet4Address) {

                        //仅仅处理ipv4
                        //获取掩码位数，通过 calcMaskByPrefixLength 转换为字符串
                        return calcMaskByPrefixLength(interfaceAddress.getNetworkPrefixLength());
                    }
                }
            }
        } catch (SocketException e) {

            e.printStackTrace();
        }

        return "0.0.0.0";
    }

    /*通过子网掩码的位数计算子网掩码*/
    private static String calcMaskByPrefixLength(int length) {

        int mask = 0xffffffff << (32 - length);
        int partsNum = 4;
        int bitsOfPart = 8;
        int maskParts[] = new int[partsNum];
        int selector = 0x000000ff;

        for (int i = 0; i < maskParts.length; i++) {

            int pos = maskParts.length - 1 - i;
            maskParts[pos] = (mask >> (i * bitsOfPart)) & selector;
        }

        String result = "";
        result = result + maskParts[0];

        for (int i = 1; i < maskParts.length; i++) {

            result = result + "." + maskParts[i];
        }

        return result;
    }

    public static String getGateWay() {

        String [] arr;

        try {

            Process  process = Runtime.getRuntime().exec("ip route list table 0");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String string = in.readLine();
            arr = string.split("\\s+");
            return arr[2];
        } catch (IOException e) {

            e.printStackTrace();
        }

        return "0.0.0.0";
    }

    /*获取服务id*/
    public static String getServerNetIp(final Context context) {

        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        if (dhcpInfo == null) {

            return null;
        }
        int serverAddress = dhcpInfo.serverAddress;
        return intToIp(serverAddress);
    }

    /*获取子网掩码*/
    public static String getSubnetMask(final Context context) {

        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        if (dhcpInfo == null) {

            return null;
        }
        int netMask = dhcpInfo.netmask;
        return intToIp(netMask);
    }

    /*获取子网掩码*/
    public static String getGateway(final Context context) {

        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        if (dhcpInfo == null) {

            return null;
        }
        int gateway = dhcpInfo.gateway;
        return intToIp(gateway);
    }

    /*获取wifi ip地址*/
    public static String getWifiIp(final Context context) {

        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo == null) {

            return null;
        }

        int gateway = wifiInfo.getIpAddress();
        return intToIp(gateway);
    }

    /*获取mac ip地址*/
    public static String getMacAddress(final Context context) {

        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo == null) {

            return null;
        }

        return wifiInfo.getMacAddress();
    }

    public static int obtainConnectedNetType(final Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {

            return Param.NET_NONE;
        }

        if (netConnected(context)) {

            int netType = networkInfo.getType();
            if (netType == ConnectivityManager.TYPE_WIFI) {

                return Param.NET_WIFI;
            } else if (netType == ConnectivityManager.TYPE_MOBILE) {

                return Param.NET_MOBILE;
            }
        }

        return Param.NET_NONE;
    }

    private static final String intToIp(int paramInt) {

        return (paramInt & 0xFF) + Param.DOT
                + (0xFF & paramInt >> 8) + Param.DOT
                + (0xFF & paramInt >> 16) + Param.DOT
                + (0xFF & paramInt >> 24);
    }

    public interface Param {

        int NET_WIFI = 0x01;
        int NET_MOBILE = 0x02;
        int NET_NONE = 0x03;

        String DOT = ".";
    }
}
