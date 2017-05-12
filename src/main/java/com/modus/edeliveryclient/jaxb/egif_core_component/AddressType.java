//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.05.03 at 01:28:43 PM EEST 
//


package com.modus.edeliveryclient.jaxb.egif_core_component;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}IDType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PostcodeCode" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}CodeType" minOccurs="0"/>
 *         &lt;element name="PostOfficeBox" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}TextType" minOccurs="0"/>
 *         &lt;element name="StreetName" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}NameType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BuildingNumber" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}TextType" minOccurs="0"/>
 *         &lt;element name="BuildingName" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}NameType" minOccurs="0"/>
 *         &lt;element name="RoomIdentification" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}TextType" minOccurs="0"/>
 *         &lt;element name="DepartmentName" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}NameType" minOccurs="0"/>
 *         &lt;element name="FloorIdentification" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}TextType" minOccurs="0"/>
 *         &lt;element name="CitySubdivisionName" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}NameType" minOccurs="0"/>
 *         &lt;element name="PlotIdentification" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}TextType" minOccurs="0"/>
 *         &lt;element name="AttentionOf" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}TextType" minOccurs="0"/>
 *         &lt;element name="CareOf" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}TextType" minOccurs="0"/>
 *         &lt;element name="TypeCode" type="{gr:gov:egif:data:standard:UnqualifiedDataType:1}CodeType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SpecificAddressLine" type="{gr:gov:egif:data:standard:CoreComponents:AddressLine}AddressLineType" minOccurs="0"/>
 *         &lt;element name="CityCountrySubdivision" type="{gr:gov:egif:data:standard:CoreComponents:CountrySubdivision}CountrySubdivisionType" minOccurs="0"/>
 *         &lt;element name="DistrictCountrySubdivision" type="{gr:gov:egif:data:standard:CoreComponents:CountrySubdivision}CountrySubdivisionType" minOccurs="0"/>
 *         &lt;element name="RegionCountrySubdivision" type="{gr:gov:egif:data:standard:CoreComponents:CountrySubdivision}CountrySubdivisionType" minOccurs="0"/>
 *         &lt;element name="SpecificCountry" type="{gr:gov:egif:data:standard:CoreComponents:Country}CountryType" minOccurs="0"/>
 *         &lt;element name="SpecificGeographicalCoordinate" type="{gr:gov:egif:data:standard:CoreComponents:GeographicalCoordinate}GeographicalCoordinateType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressType", namespace = "gr:gov:egif:data:standard:CoreComponents:Address", propOrder = {
    "id",
    "postcodeCode",
    "postOfficeBox",
    "streetName",
    "buildingNumber",
    "buildingName",
    "roomIdentification",
    "departmentName",
    "floorIdentification",
    "citySubdivisionName",
    "plotIdentification",
    "attentionOf",
    "careOf",
    "typeCode",
    "specificAddressLine",
    "cityCountrySubdivision",
    "districtCountrySubdivision",
    "regionCountrySubdivision",
    "specificCountry",
    "specificGeographicalCoordinate"
})
public class AddressType {

    @XmlElement(name = "ID")
    protected List<IDType> id;
    @XmlElement(name = "PostcodeCode")
    protected CodeType postcodeCode;
    @XmlElement(name = "PostOfficeBox")
    protected TextType postOfficeBox;
    @XmlElement(name = "StreetName")
    protected List<NameType> streetName;
    @XmlElement(name = "BuildingNumber")
    protected TextType buildingNumber;
    @XmlElement(name = "BuildingName")
    protected NameType buildingName;
    @XmlElement(name = "RoomIdentification")
    protected TextType roomIdentification;
    @XmlElement(name = "DepartmentName")
    protected NameType departmentName;
    @XmlElement(name = "FloorIdentification")
    protected TextType floorIdentification;
    @XmlElement(name = "CitySubdivisionName")
    protected NameType citySubdivisionName;
    @XmlElement(name = "PlotIdentification")
    protected TextType plotIdentification;
    @XmlElement(name = "AttentionOf")
    protected TextType attentionOf;
    @XmlElement(name = "CareOf")
    protected TextType careOf;
    @XmlElement(name = "TypeCode")
    protected List<CodeType> typeCode;
    @XmlElement(name = "SpecificAddressLine")
    protected AddressLineType specificAddressLine;
    @XmlElement(name = "CityCountrySubdivision")
    protected CountrySubdivisionType cityCountrySubdivision;
    @XmlElement(name = "DistrictCountrySubdivision")
    protected CountrySubdivisionType districtCountrySubdivision;
    @XmlElement(name = "RegionCountrySubdivision")
    protected CountrySubdivisionType regionCountrySubdivision;
    @XmlElement(name = "SpecificCountry")
    protected CountryType specificCountry;
    @XmlElement(name = "SpecificGeographicalCoordinate")
    protected List<GeographicalCoordinateType> specificGeographicalCoordinate;

    /**
     * Gets the value of the id property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the id property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IDType }
     * 
     * 
     */
    public List<IDType> getID() {
        if (id == null) {
            id = new ArrayList<IDType>();
        }
        return this.id;
    }

    /**
     * Gets the value of the postcodeCode property.
     * 
     * @return
     *     possible object is
     *     {@link CodeType }
     *     
     */
    public CodeType getPostcodeCode() {
        return postcodeCode;
    }

    /**
     * Sets the value of the postcodeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeType }
     *     
     */
    public void setPostcodeCode(CodeType value) {
        this.postcodeCode = value;
    }

    /**
     * Gets the value of the postOfficeBox property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getPostOfficeBox() {
        return postOfficeBox;
    }

    /**
     * Sets the value of the postOfficeBox property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setPostOfficeBox(TextType value) {
        this.postOfficeBox = value;
    }

    /**
     * Gets the value of the streetName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the streetName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStreetName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameType }
     * 
     * 
     */
    public List<NameType> getStreetName() {
        if (streetName == null) {
            streetName = new ArrayList<NameType>();
        }
        return this.streetName;
    }

    /**
     * Gets the value of the buildingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * Sets the value of the buildingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setBuildingNumber(TextType value) {
        this.buildingNumber = value;
    }

    /**
     * Gets the value of the buildingName property.
     * 
     * @return
     *     possible object is
     *     {@link NameType }
     *     
     */
    public NameType getBuildingName() {
        return buildingName;
    }

    /**
     * Sets the value of the buildingName property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameType }
     *     
     */
    public void setBuildingName(NameType value) {
        this.buildingName = value;
    }

    /**
     * Gets the value of the roomIdentification property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getRoomIdentification() {
        return roomIdentification;
    }

    /**
     * Sets the value of the roomIdentification property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setRoomIdentification(TextType value) {
        this.roomIdentification = value;
    }

    /**
     * Gets the value of the departmentName property.
     * 
     * @return
     *     possible object is
     *     {@link NameType }
     *     
     */
    public NameType getDepartmentName() {
        return departmentName;
    }

    /**
     * Sets the value of the departmentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameType }
     *     
     */
    public void setDepartmentName(NameType value) {
        this.departmentName = value;
    }

    /**
     * Gets the value of the floorIdentification property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getFloorIdentification() {
        return floorIdentification;
    }

    /**
     * Sets the value of the floorIdentification property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setFloorIdentification(TextType value) {
        this.floorIdentification = value;
    }

    /**
     * Gets the value of the citySubdivisionName property.
     * 
     * @return
     *     possible object is
     *     {@link NameType }
     *     
     */
    public NameType getCitySubdivisionName() {
        return citySubdivisionName;
    }

    /**
     * Sets the value of the citySubdivisionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameType }
     *     
     */
    public void setCitySubdivisionName(NameType value) {
        this.citySubdivisionName = value;
    }

    /**
     * Gets the value of the plotIdentification property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getPlotIdentification() {
        return plotIdentification;
    }

    /**
     * Sets the value of the plotIdentification property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setPlotIdentification(TextType value) {
        this.plotIdentification = value;
    }

    /**
     * Gets the value of the attentionOf property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getAttentionOf() {
        return attentionOf;
    }

    /**
     * Sets the value of the attentionOf property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setAttentionOf(TextType value) {
        this.attentionOf = value;
    }

    /**
     * Gets the value of the careOf property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getCareOf() {
        return careOf;
    }

    /**
     * Sets the value of the careOf property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setCareOf(TextType value) {
        this.careOf = value;
    }

    /**
     * Gets the value of the typeCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the typeCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTypeCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodeType }
     * 
     * 
     */
    public List<CodeType> getTypeCode() {
        if (typeCode == null) {
            typeCode = new ArrayList<CodeType>();
        }
        return this.typeCode;
    }

    /**
     * Gets the value of the specificAddressLine property.
     * 
     * @return
     *     possible object is
     *     {@link AddressLineType }
     *     
     */
    public AddressLineType getSpecificAddressLine() {
        return specificAddressLine;
    }

    /**
     * Sets the value of the specificAddressLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressLineType }
     *     
     */
    public void setSpecificAddressLine(AddressLineType value) {
        this.specificAddressLine = value;
    }

    /**
     * Gets the value of the cityCountrySubdivision property.
     * 
     * @return
     *     possible object is
     *     {@link CountrySubdivisionType }
     *     
     */
    public CountrySubdivisionType getCityCountrySubdivision() {
        return cityCountrySubdivision;
    }

    /**
     * Sets the value of the cityCountrySubdivision property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountrySubdivisionType }
     *     
     */
    public void setCityCountrySubdivision(CountrySubdivisionType value) {
        this.cityCountrySubdivision = value;
    }

    /**
     * Gets the value of the districtCountrySubdivision property.
     * 
     * @return
     *     possible object is
     *     {@link CountrySubdivisionType }
     *     
     */
    public CountrySubdivisionType getDistrictCountrySubdivision() {
        return districtCountrySubdivision;
    }

    /**
     * Sets the value of the districtCountrySubdivision property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountrySubdivisionType }
     *     
     */
    public void setDistrictCountrySubdivision(CountrySubdivisionType value) {
        this.districtCountrySubdivision = value;
    }

    /**
     * Gets the value of the regionCountrySubdivision property.
     * 
     * @return
     *     possible object is
     *     {@link CountrySubdivisionType }
     *     
     */
    public CountrySubdivisionType getRegionCountrySubdivision() {
        return regionCountrySubdivision;
    }

    /**
     * Sets the value of the regionCountrySubdivision property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountrySubdivisionType }
     *     
     */
    public void setRegionCountrySubdivision(CountrySubdivisionType value) {
        this.regionCountrySubdivision = value;
    }

    /**
     * Gets the value of the specificCountry property.
     * 
     * @return
     *     possible object is
     *     {@link CountryType }
     *     
     */
    public CountryType getSpecificCountry() {
        return specificCountry;
    }

    /**
     * Sets the value of the specificCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryType }
     *     
     */
    public void setSpecificCountry(CountryType value) {
        this.specificCountry = value;
    }

    /**
     * Gets the value of the specificGeographicalCoordinate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the specificGeographicalCoordinate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSpecificGeographicalCoordinate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GeographicalCoordinateType }
     * 
     * 
     */
    public List<GeographicalCoordinateType> getSpecificGeographicalCoordinate() {
        if (specificGeographicalCoordinate == null) {
            specificGeographicalCoordinate = new ArrayList<GeographicalCoordinateType>();
        }
        return this.specificGeographicalCoordinate;
    }

}
