package com.mvcmasters.ems.model;

/**
 * Represents a tree structure model,
 * commonly used for representing hierarchical data.
 */
public class TreeModel {

    /**
     * The unique identifier of this tree node.
     */
    private Integer id;
    /**
     * The identifier of the parent node.
     * This is used to establish the hierarchical
     * relationship in the tree structure.
     * A null or specific value can represent a root node.
     */
    private Integer pId;
    /**
     * The name of the tree node. This can be
     * displayed in a UI representing the tree.
     */
    private String name;
    /**
     * Indicates whether the tree node is selected
     * or checked. This is often used in UIs
     * where nodes can be selected or marked.
     */
    private boolean checked = false;

    /**
     * Gets the ID of the tree node.
     *
     * @return The ID of the node.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of the tree node.
     *
     * @param iD The ID to set for the node.
     */
    public void setId(final Integer iD) {
        this.id = iD;
    }

    /**
     * Gets the parent ID of this tree node.
     *
     * @return The parent ID of the node.
     */
    public Integer getpId() {
        return pId;
    }

    /**
     * Sets the parent ID for this tree node.
     *
     * @param pID The parent ID to set for the node.
     */
    public void setpId(final Integer pID) {
        this.pId = pID;
    }

    /**
     * Gets the name of the tree node.
     *
     * @return The name of the node.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the tree node.
     *
     * @param pname The name to set for the node.
     */
    public void setName(final String pname) {
        this.name = pname;
    }

    /**
     * Checks whether the tree node is selected or not.
     *
     * @return True if the node is selected, false otherwise.
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * Sets the selection state of the tree node.
     *
     * @param check The selection state to set for the node.
     */
    public void setChecked(final boolean check) {
        this.checked = check;
    }
}
