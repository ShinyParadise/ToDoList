package com.example.todolist.models;

import java.util.ArrayList;

public class ToDoList {
    private ArrayList<String> paragraphs;

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
