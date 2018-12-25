package ru.msaitov.practice.dao.additionalFunctions;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Конкатенация HQL запроса при работе с полями
 */
@Repository
public class AddingQueryString {

    private List<String> strQuaryList = new ArrayList<>();
    private String firstWord;
    private String separator;
    private String tableName;
    private String s;
    private String o;

    /**
     * Необязательное поле, установить имя переменной
     *
     * @param s
     */
    public void setS(final String s) {
        this.s = s;
    }

    /**
     * Необязательное поле, установить значение переменной
     *
     * @param o
     */
    public void setO(final String o) {
        this.o = o;
    }

    /**
     * Имя таблицы
     *
     * @param tableName
     */
    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    /**
     * Добавить имя поля
     *
     * @param strQuary
     */
    public void add(final String strQuary) {
        strQuaryList.add(strQuary);
    }

    /**
     * Генерация строки запроса
     *
     * @return
     */
    public String generateString() {
        if (strQuaryList.size() == 0) {
            return "";
        }
        String addQuery = " ";
        String genField;
        for (int i = 0; i < strQuaryList.size(); i++) {
            if (s == null) {
                s = strQuaryList.get(i);
            }
            if (o == null) {
                o = strQuaryList.get(i);
            }
            if (tableName == null) {
                genField = strQuaryList.get(i);
            } else {
                genField = tableName + "." + s + " = :" + o;
            }
            if (i == 0) {
                addQuery += " " + firstWord + " " + genField;
            } else {
                addQuery += " " + separator + " " + genField;
            }
            s = null;
            o = null;
        }
        addQuery += " ";
        return addQuery;
    }

    /**
     * Установить команду запроса, например: WHERE или SET
     *
     * @param firstWord
     */
    public void setFirstWord(final String firstWord) {
        this.firstWord = firstWord;
    }

    /**
     * Установить разделитель, например AND или ","
     *
     * @param separator
     */
    public void setSeparator(final String separator) {
        this.separator = separator;
    }

    /**
     * Очистить все поля
     */
    public void clear() {
        strQuaryList.clear();
        firstWord = null;
        separator = null;
        tableName = null;
        s = null;
        o = null;
    }


}
