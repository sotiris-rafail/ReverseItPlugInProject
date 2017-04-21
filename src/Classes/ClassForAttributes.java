/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author swthrhs
 */
package Classes;
public class ClassForAttributes {
    private String access;
    private String type;
    private String attributeName;

    public ClassForAttributes(String access, String type, String attributeName) {
        this.access = access;
        this.type = type;
        this.attributeName = attributeName;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    @Override
    public String toString() {
        return "ClassForAttributes{" +
                "access='" + access + '\'' +
                ", type='" + type + '\'' +
                ", attributeName='" + attributeName + '\'' +
                '}';
    }
}
