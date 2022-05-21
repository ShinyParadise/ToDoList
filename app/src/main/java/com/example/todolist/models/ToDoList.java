package com.example.todolist.models;

import java.util.ArrayList;

public class ToDoList {
    private String listName;

    private String description;

    private ArrayList<String> paragraphs;

    public ToDoList(String name, String descr) {
        listName = name;
        description = descr;
    }


    public ArrayList<String> getParagraphs() {
        return paragraphs;
    }

    public void addParagraph(String paragraphToAdd) {
        paragraphs.add(paragraphToAdd);
    }

    public void changeParagraph(String oldParagraph, String changedParagraph) {
        int indexOfOldParagraph = paragraphs.indexOf(oldParagraph);
        paragraphs.set(indexOfOldParagraph, changedParagraph);
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setParagraphs(ArrayList<String> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public void deleteParagraph(String paragraphToDelete) {
        paragraphs.remove(paragraphToDelete);
    }

    public void clearAllParagraphs() {
        paragraphs.clear();
    }
}
