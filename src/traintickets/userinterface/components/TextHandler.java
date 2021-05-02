/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package traintickets.userinterface.components;

/**
 *
 * @author koumudi
 */
   public class TextHandler {
        private String text = "";
        private boolean editing;

        public void add(char c) {
            text += c;
            System.out.println("Text : "+ text);
            editing = true;
        }

        public void removeCharAtEnd() {
            if (text.length() > 0) {
                text = text.substring(0, text.length() - 1);
                editing = true;
            }
        }

        public void reset() {
            text = "";
            editing = false;
        }

        public String getText() {
            return text;
        }

        public boolean isEditing() {
            return editing;
        }
    }
