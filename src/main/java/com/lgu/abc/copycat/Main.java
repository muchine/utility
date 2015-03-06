package com.lgu.abc.copycat;

import java.nio.ByteBuffer;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println(convert(100000));
		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		
//		String line = null;
//		while ((line = reader.readLine()) != null) {
//			int count = Integer.parseInt(line);
//			for (int i = 0; i < count; i++) {
//				System.out.println("Hello Algospot!");
//			}
//		}
	}
	
	private static int convert(Integer number) {
		ByteBuffer buffer = ByteBuffer.allocate(4);
		buffer.putInt(number);
		byte[] bytes = buffer.array();
		
		reverse(bytes);
		return bytesToInt(bytes);
	}
	
	private static void reverse(byte[] bytes) {
		printBytes(bytes);
		for (int i = 0; i < bytes.length / 2; i++) {
			byte temp = bytes[i];
			bytes[i] = bytes[bytes.length - 1 - i];
			bytes[bytes.length - 1 - i] = temp;
		}
		printBytes(bytes);		
	}
	
	private static int bytesToInt(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.wrap(bytes);
		return buffer.getInt();
	}
	
	private static void printBytes(byte[] bytes) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			builder.append(bytes[i]).append(" ");
		}
		
		System.out.println(builder.toString());
	}
	
}
