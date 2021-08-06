package com.chaoxing.xnote.domain;

import java.io.Serializable;

/**
 * 文件夹实体
 */
public class NoteFolder  implements Serializable {

    private String folderName;
    private String isOpen;
    private int noteCount;

    public NoteFolder() {
    }

    public NoteFolder(String folderName, String isOpen, int noteCount) {
        this.folderName = folderName;
        this.isOpen = isOpen;
        this.noteCount = noteCount;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public int getNoteCount() {
        return noteCount;
    }

    public void setNoteCount(int noteCount) {
        this.noteCount = noteCount;
    }

    @Override
    public String toString() {
        return "NoteFolder{" +
                "folderName='" + folderName + '\'' +
                ", isOpen='" + isOpen + '\'' +
                ", noteCount=" + noteCount +
                '}';
    }
}
