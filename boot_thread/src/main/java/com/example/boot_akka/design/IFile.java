package com.example.boot_akka.design;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/28
 * 组合模式
 */
public interface IFile {
    void delete();
    String getName();

    //以上是公共行文，以下是文件夹才有的行为
    void createNewFile(String name);

    void deleteFile(String name);

    IFile getIFile(int index);

    /**
     * 文件夹
     */
    class Folder implements IFile {

        private String name;
        private IFile folder;
        private List<IFile> files;

        public Folder(String name) {
            this.name = name;
        }

        public Folder(String name, IFile folder) {
            this.name = name;
            this.folder = folder;
            files = new ArrayList<>();
        }

        @Override
        public void delete() {
            List<IFile> copy = new ArrayList<>(files);
            System.out.println("-------------删除子文件------------");
            copy.forEach(IFile::delete);

            System.out.println("-------------删除子文件结束-----------");
            if (folder != null) {
                folder.deleteFile(name);
            }
            System.out.println("---删除[" + name + "]----");
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void createNewFile(String name) {
            if (name.contains(".")) {
                files.add(new File(name, this));
            } else {
                files.add(new Folder(name, this));
            }
        }

        @Override
        public void deleteFile(String name) {
            files.forEach(iFile -> {
                if (iFile.getName().equals(name)) {
                    files.remove(iFile);
                }
            });
        }

        @Override
        public IFile getIFile(int index) {
            return files.get(index);
        }
    }

    class File implements IFile {

        private String name;
        private IFile folder;

        File(String name, IFile folder) {
            super();
            this.name = name;
            this.folder = folder;
        }

        public String getName() {
            return name;
        }

        public void delete() {
            folder.deleteFile(name);
            System.out.println("---删除[" + name + "]---");
        }

        //文件不支持创建新文件
        public void createNewFile(String name) {
            throw new UnsupportedOperationException();
        }
        //文件不支持删除文件
        public void deleteFile(String name) {
            throw new UnsupportedOperationException();
        }
        //文件不支持获取下面的文件列表
        public IFile getIFile(int index) {
            throw new UnsupportedOperationException();
        }
    }
}
