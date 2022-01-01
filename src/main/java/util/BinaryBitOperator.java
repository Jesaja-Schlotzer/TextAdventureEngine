package util;

/**
 * The BinaryBitOperator is made for binary operations on two int variables.</br></br>
 * 
 * It contains all operations possible for two operants.
 *
 */


public interface BinaryBitOperator {
	
	int operate(int in1, int in2);
	
	
	
	
	
	/**
     * <style>
     *   table {
     *     border-collapse: collapse;
     *     border: 2px solid;
     *     text-align: center;
     *   }
     *   
     *   th {
     *     border: 2px solid;
     *   }
     *   
     *   td {
     *     border: 1px solid;
     *     padding: 4px;
     *     line-height: 0px;
     *     height: 17px;
     *   }
     *   
     *   .out  {
     *     border-left: 2px solid;
     *   }
     *   
     * </style>
     * 
     * 
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _0000 = (in1, in2) -> 0;
	
	
	
	
	
	
	/**
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _1000 = (in1, in2) -> ~(in1 | in2);
	
	
	
	
	
	
	/**
     * <style>
     *   table {
     *     border-collapse: collapse;
     *     border: 2px solid;
     *     text-align: center;
     *   }
     *   
     *   th {
     *     border: 2px solid;
     *   }
     *   
     *   td {
     *     border: 1px solid;
     *     padding: 4px;
     *     line-height: 0px;
     *     height: 17px;
     *   }
     *   
     *   .out  {
     *     border-left: 2px solid;
     *   }
     *   
     * </style>
     * 
     * 
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _0100 = (in1, in2) -> (in2 & in1) ^ in1;
	
	
	
	
	
	
	/**
     * <style>
     *   table {
     *     border-collapse: collapse;
     *     border: 2px solid;
     *     text-align: center;
     *   }
     *   
     *   th {
     *     border: 2px solid;
     *   }
     *   
     *   td {
     *     border: 1px solid;
     *     padding: 4px;
     *     line-height: 0px;
     *     height: 17px;
     *   }
     *   
     *   .out  {
     *     border-left: 2px solid;
     *   }
     *   
     * </style>
     * 
     * 
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _1100 = (in1, in2) -> ~((in1 | in2) & in2);
	
	
	
	
	
	
	/**
     * <style>
     *   table {
     *     border-collapse: collapse;
     *     border: 2px solid;
     *     text-align: center;
     *   }
     *   
     *   th {
     *     border: 2px solid;
     *   }
     *   
     *   td {
     *     border: 1px solid;
     *     padding: 4px;
     *     line-height: 0px;
     *     height: 17px;
     *   }
     *   
     *   .out  {
     *     border-left: 2px solid;
     *   }
     *   
     * </style>
     * 
     * 
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _0010 = (in1, in2) -> (in1 & in2) ^ in2;
	
	
	
	
	
	
	/**
     * <style>
     *   table {
     *     border-collapse: collapse;
     *     border: 2px solid;
     *     text-align: center;
     *   }
     *   
     *   th {
     *     border: 2px solid;
     *   }
     *   
     *   td {
     *     border: 1px solid;
     *     padding: 4px;
     *     line-height: 0px;
     *     height: 17px;
     *   }
     *   
     *   .out  {
     *     border-left: 2px solid;
     *   }
     *   
     * </style>
     * 
     * 
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _1010 = (in1, in2) -> ~in1;
	
	
	
	
	
	
	/**
     * <style>
     *   table {
     *     border-collapse: collapse;
     *     border: 2px solid;
     *     text-align: center;
     *   }
     *   
     *   th {
     *     border: 2px solid;
     *   }
     *   
     *   td {
     *     border: 1px solid;
     *     padding: 4px;
     *     line-height: 0px;
     *     height: 17px;
     *   }
     *   
     *   .out  {
     *     border-left: 2px solid;
     *   }
     *   
     * </style>
     * 
     * 
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _0110 = (in1, in2) -> in1 ^ in2;
	
	
	
	
	
	/**
     * <style>
     *   table {
     *     border-collapse: collapse;
     *     border: 2px solid;
     *     text-align: center;
     *   }
     *   
     *   th {
     *     border: 2px solid;
     *   }
     *   
     *   td {
     *     border: 1px solid;
     *     padding: 4px;
     *     line-height: 0px;
     *     height: 17px;
     *   }
     *   
     *   .out  {
     *     border-left: 2px solid;
     *   }
     *   
     * </style>
     * 
     * 
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _1110 = (in1, in2) -> ~(in1 & in2);

	
	
	
	
	/**
     * <style>
     *   table {
     *     border-collapse: collapse;
     *     border: 2px solid;
     *     text-align: center;
     *   }
     *   
     *   th {
     *     border: 2px solid;
     *   }
     *   
     *   td {
     *     border: 1px solid;
     *     padding: 4px;
     *     line-height: 0px;
     *     height: 17px;
     *   }
     *   
     *   .out  {
     *     border-left: 2px solid;
     *   }
     *   
     * </style>
     * 
     * 
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _0001 = (in1, in2) -> in1 & in2;
	
	
	
	
	
	/**
     * <style>
     *   table {
     *     border-collapse: collapse;
     *     border: 2px solid;
     *     text-align: center;
     *   }
     *   
     *   th {
     *     border: 2px solid;
     *   }
     *   
     *   td {
     *     border: 1px solid;
     *     padding: 4px;
     *     line-height: 0px;
     *     height: 17px;
     *   }
     *   
     *   .out  {
     *     border-left: 2px solid;
     *   }
     *   
     * </style>
     * 
     * 
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _1001 = (in1, in2) -> ~(in1 ^ in2);
	
	
	
	
	
	/**
     * <style>
     *   table {
     *     border-collapse: collapse;
     *     border: 2px solid;
     *     text-align: center;
     *   }
     *   
     *   th {
     *     border: 2px solid;
     *   }
     *   
     *   td {
     *     border: 1px solid;
     *     padding: 4px;
     *     line-height: 0px;
     *     height: 17px;
     *   }
     *   
     *   .out  {
     *     border-left: 2px solid;
     *   }
     *   
     * </style>
     * 
     * 
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _0101 = (in1, in2) -> in1;
	
	
	
	
	
	/**
     * <style>
     *   table {
     *     border-collapse: collapse;
     *     border: 2px solid;
     *     text-align: center;
     *   }
     *   
     *   th {
     *     border: 2px solid;
     *   }
     *   
     *   td {
     *     border: 1px solid;
     *     padding: 4px;
     *     line-height: 0px;
     *     height: 17px;
     *   }
     *   
     *   .out  {
     *     border-left: 2px solid;
     *   }
     *   
     * </style>
     * 
     * 
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _1101 = (in1, in2) -> ~((in1 ^ in2) & in2);

	
	
	
	
	/**
     * <style>
     *   table {
     *     border-collapse: collapse;
     *     border: 2px solid;
     *     text-align: center;
     *   }
     *   
     *   th {
     *     border: 2px solid;
     *   }
     *   
     *   td {
     *     border: 1px solid;
     *     padding: 4px;
     *     line-height: 0px;
     *     height: 17px;
     *   }
     *   
     *   .out  {
     *     border-left: 2px solid;
     *   }
     *   
     * </style>
     * 
     * 
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _0011 = (in1, in2) -> (in1 | in2) & in2;
	
	
	
	
	
	/**
     * <style>
     *   table {
     *     border-collapse: collapse;
     *     border: 2px solid;
     *     text-align: center;
     *   }
     *   
     *   th {
     *     border: 2px solid;
     *   }
     *   
     *   td {
     *     border: 1px solid;
     *     padding: 4px;
     *     line-height: 0px;
     *     height: 17px;
     *   }
     *   
     *   .out  {
     *     border-left: 2px solid;
     *   }
     *   
     * </style>
     * 
     * 
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _1011 = (in1, in2) -> ~((in2 & in1) ^ in1);
	
	
	
	
	
	/**
     * <style>
     *   table {
     *     border-collapse: collapse;
     *     border: 2px solid;
     *     text-align: center;
     *   }
     *   
     *   th {
     *     border: 2px solid;
     *   }
     *   
     *   td {
     *     border: 1px solid;
     *     padding: 4px;
     *     line-height: 0px;
     *     height: 17px;
     *   }
     *   
     *   .out  {
     *     border-left: 2px solid;
     *   }
     *   
     * </style>
     * 
     * 
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">0</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _0111 = (in1, in2) -> in1 | in2;
	
	
	
	
	
	/**
     * <style>
     *   table {
     *     border-collapse: collapse;
     *     border: 2px solid;
     *     text-align: center;
     *   }
     *   
     *   th {
     *     border: 2px solid;
     *   }
     *   
     *   td {
     *     border: 1px solid;
     *     padding: 4px;
     *     line-height: 0px;
     *     height: 17px;
     *   }
     *   
     *   .out  {
     *     border-left: 2px solid;
     *   }
     *   
     * </style>
     * 
     * 
     * <table>
     * 	 <tr>
     *     <th>in1</th>
     *     <th>in2</th>
     *     <th>out</th>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>0</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>0</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     *   <tr>
     *     <td>1</td>
     *     <td>1</td>
     *     <td class="out">1</td>
     *   </tr>
     * </table>
     * 
     * @param in1   First input Bit-Set
     * @param in2   Second input Bit-Set
     * 
     * @return returns operated Bit-Set
     */
	
	public static final BinaryBitOperator _1111 = (in1, in2) -> -1;
}
