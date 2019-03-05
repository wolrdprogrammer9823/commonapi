package tool.common_useage_apis;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    private static List<String> targetFilePathList = new ArrayList<>();

    /*导出数据*/
    public static <T extends BaseRowModel> void exportData(String path, String fileName, List<T> dataSet, T t) {

        exportData(path, fileName, null, dataSet, t);
    }

    public static <T extends BaseRowModel> void exportData(String path, String fileName, String sheetName, List<T> dataSet, T t) {

        File rootFile = new File(path);
        if (!rootFile.exists()) {

            rootFile.mkdirs();
        }

        if (!fileName.endsWith(ExcelTypeEnum.XLSX.getValue()) || !fileName.endsWith(ExcelTypeEnum.XLS.getValue())) {

            fileName += ExcelTypeEnum.XLSX.getValue();
        }

        boolean xlsxFile = fileName.endsWith(ExcelTypeEnum.XLSX.getValue());

        File storeFile = new File(rootFile, fileName);
        if (!storeFile.exists() && storeFile.isFile()) {

            try {

                storeFile.createNewFile();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        FileOutputStream fileOutputStream;
        try {

            fileOutputStream = new FileOutputStream(storeFile);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
            return;
        }

        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ExcelWriter excelWriter = new ExcelWriter(bufferedOutputStream, xlsxFile ? ExcelTypeEnum.XLSX : ExcelTypeEnum.XLS);
        Sheet sheet = new Sheet(1, 1, t.getClass(), sheetName, null);
        excelWriter.write(dataSet, sheet);
        excelWriter.finish();

        FileUtils.closeIO(bufferedOutputStream, fileOutputStream);
    }

    public static <T extends BaseRowModel> List<T> importData(String path, T t) {

        if (CUATextUtil.isEmpty(path)) {

            return null;
        }

        File file = new File(path);
        if (!file.exists() || !file.getAbsolutePath().endsWith(ExcelTypeEnum.XLSX.getValue())
                           || !file.getAbsolutePath().endsWith(ExcelTypeEnum.XLS.getValue())) {

            return null;
        }

        boolean xlsxFile = file.getAbsolutePath().endsWith(ExcelTypeEnum.XLSX.getValue());

        FileInputStream fis;
        try {

            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
            return null;
        }

        final List<T> dataSet = new ArrayList<>();
        BufferedInputStream bis = new BufferedInputStream(fis);
        ExcelReader excelReader = new ExcelReader(bis, xlsxFile ? ExcelTypeEnum.XLSX : ExcelTypeEnum.XLS,
                new AnalysisEventListener() {
                    @Override
                    public void invoke(Object object, AnalysisContext context) {

                        T t = (T) object;
                        dataSet.add(t);
                    }
                    @Override
                    public void doAfterAllAnalysed(AnalysisContext context) {}
                });

        excelReader.read(new Sheet(1, 1, t.getClass()));
        return dataSet;
    }

    public static List<String> findTargetFile(String pathName) {

        File file = new File(pathName);
        if (!file.exists()) {

            return null;
        }

        File[] subFiles = file.listFiles();
        if (subFiles == null) {

            return null;
        }

        for (File subFile : subFiles) {

            if (subFile.isFile()) {

                String fileName = subFile.getName();
                if (fileName.endsWith("xls")) {

                    String subFilePath = subFile.getAbsolutePath() + File.separator + subFile.getName() + ".xls";
                    targetFilePathList.add(subFilePath);
                } else if (fileName.endsWith("xlsx")) {

                    String subFilePath = subFile.getAbsolutePath() + File.separator + subFile.getName() + ".xlsx";
                    targetFilePathList.add(subFilePath);
                }
            }

            if (subFile.isDirectory()) {

                String directoryPathName = subFile.getAbsolutePath();
                findTargetFile(directoryPathName);
            }
        }

        return targetFilePathList;
    }

    public static void clearTargetFile() {

        targetFilePathList.clear();
    }
}
