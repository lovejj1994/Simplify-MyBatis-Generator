package org.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.*;

import java.util.Iterator;
import java.util.List;

public class SimplifyMapperPlugin extends MapperPlugin {


    /**
     * 添加lombok
     *
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addAnnotation("@Data");
        super.processEntityClass(topLevelClass, introspectedTable);
        return true;
    }

    /**
     * 不生成set方法
     *
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, Plugin.ModelClassType modelClassType) {
        return false;
    }

    /**
     * 不生成 get 方法
     *
     * @param method
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, Plugin.ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean sqlMapBaseColumnListElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return true;
    }

    @Override
    public boolean sqlMapBlobColumnListElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        this.generateSqlBaseColumns(document, introspectedTable);
        return true;
    }

    /**
     * 生成 Base_Column_List
     *
     * @param document
     * @param introspectedTable
     */
    private void generateSqlBaseColumns(Document document, IntrospectedTable introspectedTable) {
        XmlElement rootElement = document.getRootElement();


        List<Element> elements = rootElement.getElements();
        for (Element element : elements) {
            if (element instanceof XmlElement) {
                List<Attribute> attributes = ((XmlElement) element).getAttributes();
                for (Attribute attribute : attributes) {
                    if ("Base_Column_List".equals(attribute.getValue())) {
                        return;
                    }
                }
            }
        }


        XmlElement sqlElement = new XmlElement("sql");
        Attribute attr = new Attribute("id", "Base_Column_List");
        sqlElement.addAttribute(attr);
        StringBuilder columnsBuilder = new StringBuilder();
        List columnList = introspectedTable.getAllColumns();
        Iterator columns = columnList.iterator();
        while (columns.hasNext()) {
            IntrospectedColumn content = (IntrospectedColumn) columns.next();
            columnsBuilder.append(content.getActualColumnName()).append(", ");
        }
        String columns1 = columnsBuilder.substring(0, columnsBuilder.length() - 2);
        TextElement content1 = new TextElement(columns1);
        sqlElement.addElement(content1);
        rootElement.addElement(new TextElement(""));
        rootElement.addElement(sqlElement);
        rootElement.addElement(new TextElement(""));

    }
}
